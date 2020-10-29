package com.imooc.mall.enums;

import lombok.Getter;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 17:10
 */

/**
 * '角色0-管理员,1-普通用户'
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1),
    ;
    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }

}
