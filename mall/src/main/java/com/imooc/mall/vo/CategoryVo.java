package com.imooc.mall.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by 廖师兄
 */
@Data
public class CategoryVo {
    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", sortOrder=" + sortOrder +
                ", subCategories=" + subCategories +
                '}';
    }

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
