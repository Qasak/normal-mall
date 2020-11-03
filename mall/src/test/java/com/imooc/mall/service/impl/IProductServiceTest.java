package com.imooc.mall.service.impl;

import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.vo.ProductDetailVo;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/31 17:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IProductServiceTest {
    @Autowired
    ProductServiceImpl productService;

    @Test
    public void list() {
        productService.list(null, 1, 2);
    }

    @Test
    public void detail() {
        ResponseVo<ProductDetailVo> responseVo = productService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}