package com.imooc.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/11/3 17:04
 */
@Data
public class ProductDetailVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
