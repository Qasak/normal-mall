package com.imooc.pay.service.impl;


import com.imooc.pay.PayApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.lly835.bestpay.enums.BestPayTypeEnum.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest extends PayApplicationTests {
    @Autowired
    private PayServiceImpl payServiceImpl;
    @Test
    public void create() {
        // new BigDecimal("0.01")
        payServiceImpl.create("12341234wjs69911", BigDecimal.valueOf(0.01), WXPAY_NATIVE);
    }

}
