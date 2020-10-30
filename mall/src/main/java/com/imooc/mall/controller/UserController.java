package com.imooc.mall.controller;

import com.imooc.mall.form.UserLoginForm;
import com.imooc.mall.form.UserRegisterForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.impl.UserServiceImpl;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.imooc.mall.consts.MallConsts.CURRENT_USER;
import static com.imooc.mall.enums.ResponseEnum.PARAM_ERROR;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 17:39
 */
@RestController
@RequestMapping
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user/register")
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
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userRegisterForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误, {}{}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        // dto
        return userService.register(user);
    }
    @PostMapping("/user/login")
    public ResponseVo login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        // 设置session
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        log.info("-> sessionId {}", session.getId());
        return userResponseVo;
    }
    // session保存在内存里面->server重启/项目重启直接没了，一般可以存到redis里
    // 改进版：token(分布式session)+redis
    //JSESSIONID : F0880DCDFC60C8245FEDE46346A4C61B
    @GetMapping("/user")
    public ResponseVo userInfo(HttpSession session) {
        log.info("-> sessionId {}", session.getId());
        User user = (User) session.getAttribute(CURRENT_USER);
        return ResponseVo.success(user);
    }
    // TODO 判断登录状态，拦截器
    /**
     *getSessionTimeoutInMinutes:过期限制至少一分钟
     */
    @PostMapping("user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("-> sessionId {}", session.getId());
        session.removeAttribute(CURRENT_USER);
        return ResponseVo.success();
    }

}
