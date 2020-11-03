package com.imooc.mall.form;

import lombok.Data;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/11/3 18:08
 */
@Data
public class CartUpdateForm {

    private Integer quantity;

    private Boolean selected;
}
