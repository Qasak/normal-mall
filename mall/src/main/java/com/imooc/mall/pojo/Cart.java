package com.imooc.mall.pojo;

import lombok.Data;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/11/3 18:10
 */
@Data
public class Cart {

    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;

    public Cart() {
    }

    public Cart(Integer productId, Integer quantity, Boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSelected = productSelected;
    }
}

