package com.imooc.mall.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 20:19
 */
@Data
public class CategoryVo {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
