package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 10:50
 */
@Data
public class UserLoginForm {
    @NotBlank()
    private String username;
    @NotBlank()
    private String password;
}
