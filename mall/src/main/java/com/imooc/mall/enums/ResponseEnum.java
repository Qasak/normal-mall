package com.imooc.mall.enums;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 10:28
 */
public enum ResponseEnum {
    ERROR(-1, "服务端异常"),
    // 所有的成功
    SUCCESS(0, "成功"),
    PASSWAORD_ERROR(1, "密码错误"),
    USER_ALREADY_EXIST(2, "用户已经存在"),
    PARAM_ERROR(3, "注册参数错误"),
    NEED_LOGIN(10, "用户未登录，无法获取当前用户信息"),

    ;
    private Integer code;

    private String name;

    ResponseEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
