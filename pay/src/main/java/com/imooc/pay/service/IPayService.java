package com.imooc.pay.service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {
    /**
     * 创建，发起支付
     * @param orderId
     * @param amount
     * @return
     */
    PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum);

    /**
     *  asyncNotify 接收来自微信的支付成功通知
     * @param notifyData
     */
    String asyncNotify(String notifyData);
}
