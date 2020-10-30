package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 10:50
 */
@Data
public class UserRegisterForm {
    // 注解：不能是空白
    // @NotBlank用于String:不能是空格
    // @NotNull:
    // @NotEmpty: 用于集合：数组是不是为空
    @NotBlank()
    private String username;
    @NotBlank()
    private String password;
    @NotBlank()
    private String email;
}
