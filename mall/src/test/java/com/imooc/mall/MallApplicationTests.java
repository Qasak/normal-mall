package com.imooc.mall;

import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.impl.UserServiceImpl;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
@MapperScan(basePackages = "com.imooc.mall.dao")
@ComponentScan(basePackages = "com.imooc.mall.service.impl")
@SpringBootTest
@Transactional
public class MallApplicationTests {
    public static final String USERNAME = "QASAK1";
    public static final String PASSWORD = "123456";
    @Autowired
    private UserServiceImpl userService;
    @Before
    public void register() {
        User user = new User(USERNAME, PASSWORD,"663012912@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
    @Test
    public void login() {
        register();
        ResponseVo responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}
