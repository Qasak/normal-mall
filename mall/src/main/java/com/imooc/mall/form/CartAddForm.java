package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/11/3 17:56
 */
@Data
public class CartAddForm {

    @NotNull
    private Integer productId;

    private Boolean selected = true;
}

