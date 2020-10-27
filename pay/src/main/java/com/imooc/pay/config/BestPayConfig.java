package com.imooc.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
// @Component: 通用的注解，可标注任意类为 Spring 组件
@Configuration
public class BestPayConfig {
    // 加上Bean: Spring容器管理功能：项目启动时执行下面的代码
    // 返回了一个BestPayService实例
    @Bean
    public BestPayService bestPayService() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wxd898fcb01713c658");
        wxPayConfig.setMchId("1483469312");
        wxPayConfig.setMchKey("098F6BCD4621D373CADE4E832627B4F6");
        wxPayConfig.setNotifyUrl("http://wongjs.natapp1.cc/pay/notify");

        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId("2021001196681841");
        aliPayConfig.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCed2ZwS7F+PVXncaE6LvfRCRmRgekLUCA5LBLJoxxmMgCN9GQisX9oC/4mWNKqwNsa3X6KsxHNGqNvsdPWOmb2NWci9IWcVU6jOtCG8M7p01L8eQKFbTgrx1LLojEqYDeQEKETCuiy59jmiNIPH0kbGUn95PbzhX3CYDfFhO+ROTZMMJeBAJg2b07pMxqxL/HVM07H673shfb8M3iDignYR1qtTHKd7PuWR159I4ztzJUbPpSEB8mxzbGMU37QnWodJL6IGWkpgkgiN5ItbkdmbaY22rn7n7tHKWXEFdy+LUPBSjqoH2cR0MVItYTfyQv4AKmntXVqQk04XJNmNCWlAgMBAAECggEARPQFkhWNvTWpPJVYJ34qfT3tb0BNDIwoCVihMDYWdG2LijlunR4JJ1gYlc0Olhhm/rCkkGvM8ieGOGLzn4M4LCgeobourVD9b892z1DXaC15LbzmGPLmfz4zS8JejGbjpn9l52hWywpRejbM2KGvaOg/JwhLYLmUeFDv07W9nL3N/6Tvjk9LQXeuK1LnCSDc3utrpVoJvzM5dGTXPR5vhHJrknQvzQC4b1mEc1miptaE2DX6/mUy82nIDGbA0iTMjqFc4/+4EhHHfGcfutzzO31PNN5rxjOHorms4jcV4t+GmiJdsmb+I4QkTV2XEzo9dreS04jbYnNB6mLjmTkN/QKBgQDQAdFNXUYK+L1bM2wbuF3IzodvzBBCEiM9ORNx7PuIiLuS0q4lEUv/kDk+sq22LCORnjiqAEyrr9XqL1Vi7ndT4r96MHFJiXUBrm3RXPrdtTLHjXCGOu8OO5mafp5scZohvtPc4kwqCS9GIIDWD6gIy6pIdFZLvNXBiva8oYrCPwKBgQDDB2dUz6aJZFeVMKxW+WV5eMkhnSknHbbTgw/Vl2mTm2vJPx6C+GmHa2FRGCNt2IhG6Kj+BLieVHFjLVpYvXZ4cVYOGXnRq4ILWro/n/o0A6cgmyBsLVrv54HB4d2rQtcBI3ZlDiXARjz5Bo+CXCs9dj7dIOrrRlxyRnxvN2IXGwKBgBaSm+wRWzCFncbv5aN8dnD8OKZhNsJ0xW43a2zJkuJWNETSqCNNpTmupQewzDkgwRtkWDHapndbNZOcX1/FER4BVVYlO6YHTLNKApDdzjuRFlmhd7yZWKfqv9iMNRT/nwJaexNVW36UUJUDofeY61tI/smuFk9RxsCiG/SMc0pxAoGBAJ8s/sAwzpqaRAVm+XHvEUwoMfh2Xe0oClztPlI0xx2LH1jcZFm+x9qX4JCgY+I2dVtybuMMFWZcQ0MP9AozcGrzDapqTou7MGN2BEcDmeE0DgbFtZu1FOBzEeudHh+2aObr+6iU+GjKqt8Q5LYf5ca/KKWwRSwLfE5kixD2od97AoGAVBWWRiIielcY+bfJaVP7TzAcfGqCkkiAT31/6V9wNVosTvezOM/Cng0mYxFtIkrvt+ll4wB+/r8qxYQM/y3HXjZGFwqGHxYtpmXipb/RSftX9+S8Q8wkT/N5knh7oq+6AV7jULR2yo/VCMbSrOg6NY2VpVPZyXoVpU1xF9X/Qn0=");
        aliPayConfig.setAliPayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAofgaLCRPA4IsciNv1QfENt5Nm+hGRhyC0cags6ZrbXpIRLDrNIod+ouKOSyMt1q7wBaKz+2ItR/diUmKkVLXjMaUfZyzc7QEowL50ZsUIM/9TupIung86wqbRgPOyOw9tjlnh0ypPDVsv5evJi7v6/z5ux90rAWzAWt85N5IQRuGO1fz8Z3ZX3Mkyj0okz6PrgQYV94UzlTqQ5bG7UE0oUT3ESn+NedQIA4S51+x/sUwjy3EwpW12B6TLsFpinHaSgssQ4aAGxDt346AY1XL7cO+cjoc8XBhgLrtsn5mIBCuiiMhT9ysBxLPuD3c/cHQ62DRnpy2eosO9Qlz7oN8sQIDAQAB");
        aliPayConfig.setNotifyUrl("http://wongjs.natapp1.cc/pay/notify");
        aliPayConfig.setReturnUrl("http://127.0.0.1");

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }

}
