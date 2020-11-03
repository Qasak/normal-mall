package com.imooc.mall.enums;

import lombok.Getter;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/11/3 17:10
 */
@Getter
public enum ProductStatusEnum {
    ON_SALE(1),

    OFF_SALE(2),

    DELETE(3),
    ;

    private Integer code;

    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
