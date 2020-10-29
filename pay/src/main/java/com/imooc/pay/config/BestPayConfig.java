package com.imooc.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
// @Component: 通用的注解，可标注任意类为 Spring 组件
@Configuration
public class BestPayConfig {
    // 加上Bean: Spring容器管理功能：项目启动时执行下面的代码
    // 返回了一个BestPayService实例
    @Autowired
    WxAccountConfig wxAccountConfig;
    AliPayAccountConfig aliPayAccountConfig;
    @Bean
    public BestPayService bestPayService(WxPayConfig wxPayConfig) {
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId(aliPayAccountConfig.getAppId());
        aliPayConfig.setPrivateKey(aliPayAccountConfig.getPrivateKey());
        aliPayConfig.setAliPayPublicKey(aliPayAccountConfig.getAliPayPublicKey());
        aliPayConfig.setNotifyUrl(aliPayAccountConfig.getNotifyUrl());
        aliPayConfig.setReturnUrl(aliPayAccountConfig.getReturnUrl());

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }
    @Bean
    public WxPayConfig wxPayConfig () {
        WxPayConfig wxPayConfig = new WxPayConfig();
        // appid
        wxPayConfig.setAppId(wxAccountConfig.getAppId());
        // 商户号
        wxPayConfig.setMchId(wxAccountConfig.getMchId());
        //
        wxPayConfig.setMchKey(wxAccountConfig.getMchKey());
        wxPayConfig.setNotifyUrl(wxAccountConfig.getNotifyUrl());
        wxPayConfig.setReturnUrl(wxAccountConfig.getReturnUrl());
        return wxPayConfig;
    }

}
