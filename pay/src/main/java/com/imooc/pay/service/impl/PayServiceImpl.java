package com.imooc.pay.service.impl;

import com.imooc.pay.dao.PayInfoMapper;
import com.imooc.pay.enums.PayPlatformEnum;
import com.imooc.pay.pojo.PayInfo;
import com.imooc.pay.service.IPayService;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayPlatformEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.enums.OrderStatusEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Slf4j
@Service
/**
 * @author: Wangjs
 */
public class PayServiceImpl implements IPayService {

    /**
     * @Autowired: 自动导入对象到类中，被注入进的类同样要被 Spring 容器管理。
     * 这里把SDK中一开始配置好的bestPayService对象实例注入自己的PayServiceImpl
     */
    @Autowired
    private BestPayService bestPayService;
    @Autowired
    private PayInfoMapper payInfoMapper;

    @Override
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) {
        //把创建的订单号，金额，平台信息写入数据库
        PayInfo payInfo = new PayInfo(Long.parseLong(orderId),
                PayPlatformEnum.getByBestPayTypeEnum(bestPayTypeEnum).getCode(),
                OrderStatusEnum.NOTPAY.name(),
                amount);
        payInfoMapper.insertSelective(payInfo);
        //重构点记录：
//        WxPayConfig wxPayConfig = new WxPayConfig();
//        wxPayConfig.setAppId("wxd898fcb01713c658");
//        wxPayConfig.setMchId("1483469312");
//        wxPayConfig.setMchKey("098F6BCD4621D373CADE4E832627B4F6");
//        wxPayConfig.setNotifyUrl("http://wongjs.natapp1.cc/pay/notify");

//        //这部分属于配置，单独拿出去做一个config包，并用@Bean注解，在Spring服务启动时启动
//        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//        bestPayService.setWxPayConfig(wxPayConfig);

        /**
         * 发起支付请求：
         * 1.new request实例
         * 2.设置订单名字(uid)
         * 3.设置订单号
         * 4.设置金额
         * 5.设置支付类型(包含支付宝，微信的各种类型)
         */
        if(bestPayTypeEnum != BestPayTypeEnum.WXPAY_NATIVE &&
        bestPayTypeEnum != BestPayTypeEnum.ALIPAY_PC) {
            throw new RuntimeException("暂不支持的支付类型");
        }
        PayRequest request = new PayRequest();
        request.setOrderName("9155011");
        request.setOrderId(orderId);
        request.setOrderAmount(amount.doubleValue());
        request.setPayTypeEnum(bestPayTypeEnum);

        PayResponse response = bestPayService.pay(request);
        log.info("发起支付response={}", response);
        return response;
    }
    // asyncNotify 接收来自支付宝/微信的支付成功异步通知
    @Override
    public String asyncNotify(String notifyData) {

//        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//        bestPayService.setWxPayConfig(wxPayConfig);
        /**
         * 1.为了防止伪造，需要签名校验，bestPayService已经做好了，调用之.
         */
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("异步通知payResponse={}", payResponse);
        /**
         * 2.金额校验（从数据库查订单）
         */
        PayInfo payInfo = payInfoMapper.selectByOrderNo(Long.parseLong(payResponse.getOrderId()));
        // 严重:发出告警（直接发短信）
        if(payInfo == null) {
            throw new RuntimeException("通过OrderNo查询数据库的结果是null");
        }
        // 如果订单状态不是"已支付" -> 查询金额是否一致
        if(!payInfo.getPlatformStatus().equals(OrderStatusEnum.SUCCESS.name())) {
            if(payInfo.getPayAmount().compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0) {
                //告警
                throw new RuntimeException("异步通知中的金额和数据库中不一致，orderNo = " + payResponse.getOrderId());
            }
            /**
             * 金额一致->修改订单支付状态为成功
             */
            payInfo.setPlatformStatus(OrderStatusEnum.SUCCESS.name());
            payInfo.setPlatformNumber(payResponse.getOutTradeNo());
            // 令其能自动更新时间
            payInfo.setUpdateTime(null);
            payInfoMapper.updateByPrimaryKeySelective(payInfo);
        }


        /**
         * 4.告诉微信不要再通知了
         */
        if(payResponse.getPayPlatformEnum() == BestPayPlatformEnum.WX) {
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        } else if(payResponse.getPayPlatformEnum() == BestPayPlatformEnum.ALIPAY) {
            return "success";
        }
        throw  new RuntimeException("异步通知中不支持的支付平台");
    }

    @Override
    public PayInfo queryByOrderId(String orderId) {
        return payInfoMapper.selectByOrderNo(Long.parseLong(orderId));
    }
}
