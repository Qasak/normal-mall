package com.imooc.mall.controller;

import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.UserForm;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.imooc.mall.enums.ResponseEnum.PARAM_ERROR;

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
    public ResponseVo register(@Valid @RequestBody UserForm userForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误, {}{}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }
        log.info("username:{}", userForm.getUsername());
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }
}
