package com.imooc.pay.controller;

import com.imooc.pay.pojo.PayInfo;
import com.imooc.pay.service.impl.PayServiceImpl;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/pay")
/**
 * @author:Wangjs
 */
public class PayController {

    @Autowired
    PayServiceImpl payServiceImpl;
    @Autowired
    WxPayConfig wxPayConfig;
    @GetMapping("create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount") BigDecimal amount,
                               @RequestParam("payType") BestPayTypeEnum payType){
        PayResponse payResponse = payServiceImpl.create(orderId, amount, payType);
        Map<String, String> map = new HashMap<>();
        // 支付方式不同，渲染方式不同：WX_NATIVE使用codeUrl, ALIPAY_PC使用body
        if(payType == BestPayTypeEnum.WXPAY_NATIVE) {
            map.put("codeUrl", payResponse.getCodeUrl());
            map.put("orderId", orderId);
            map.put("returnUrl", wxPayConfig.getReturnUrl());
            return new ModelAndView("createForWxNative", map);
        } else if(payType == BestPayTypeEnum.ALIPAY_PC) {
            map.put("body", payResponse.getBody());
            return new ModelAndView("createForAlipayPc", map);
        }
        throw new RuntimeException("暂不支持的类型");
    }

    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData) {
//        log.info("notifyData={}", notifyData);
        return payServiceImpl.asyncNotify(notifyData);
    }

    @GetMapping("/quereByOrderId")
    public PayInfo queryByOrderId(@RequestParam String orderId) {
        log.info("查询支付记录");
        return payServiceImpl.queryByOrderId(orderId);
    }
}
