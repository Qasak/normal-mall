package com.imooc.mall.controller;

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 17:39
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/register")
    // urlencode方式接收变量1：变量名与表单变量名不一致时，可以写 @RequestParam(value = "表单中的名字")
    //
//    public void register(@RequestParam(value = "username") String userName) {
//        log.info("username:{}", userName);
//    }

    // urlencode方式接收变量2：也可以直接把接收参数改为对象
//    public void register(User user) {
//        log.info("username:{}", user.getUsername());
//    }
    // JSON方式接收变量：需要加@RequestBody注解
    public ResponseVo register(@RequestBody User user) {
        log.info("username:{}", user.getUsername());
        return ResponseVo.success("注册成功");
    }
}
