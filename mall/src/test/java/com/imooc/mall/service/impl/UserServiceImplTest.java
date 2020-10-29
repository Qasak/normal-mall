package com.imooc.mall.service.impl;

import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 16:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void register() {
        User user = new User("wangjingsen1996", "12345", "663012911@gmail.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
}