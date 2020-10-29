package com.imooc.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 11:48
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayAccountConfig {
    private String appId;
    private String privateKey;
    private String aliPayPublicKey;
    private String notifyUrl;
    private String returnUrl;
}
