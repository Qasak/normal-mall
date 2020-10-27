package com.imooc.pay.service.impl;

import com.imooc.pay.service.IPayService;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
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
    @Override
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) {
        //写入数据库
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
        log.info("response={}", response);
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
        log.info("payResponse={}", payResponse);
        /**
         * 2.金额校验（从数据库查订单）
         */

        /**
         * 3.修改订单支付状态
         */

        /**
         * 4.告诉微信不要再通知了
         */
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }
}
