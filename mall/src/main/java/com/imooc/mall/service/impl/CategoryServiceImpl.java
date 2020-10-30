package com.imooc.mall.service.impl;

import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import com.imooc.mall.service.ICategoryService;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.imooc.mall.consts.MallConsts.ROOT_PARENT_ID;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 20:22
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryVo> categoryVoList = new ArrayList<>();
        // 查出parent_id = 0
        for(Category category : categories) {
            if(category.getParentId().equals(ROOT_PARENT_ID)) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            }
        }
        // lambda + stream
//        List<CategoryVo> categoryVoList = categories.stream().filter(e -> e.getParentId().equals(ROOT_PARENT_ID))
//                .map(this::category2CategoryVo)
//                .collect(Collectors.toList());
        // 递归查询子目录
        findSubCategory(categoryVoList, categories);
        return ResponseVo.success(categoryVoList);
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
        for(CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for(Category category : categories) {
                if(categoryVo.getId().equals(category.getParentId())) {
                    subCategoryVoList.add(category2CategoryVo(category));
                }
            }
            categoryVo.setSubCategories(subCategoryVoList);
            findSubCategory(subCategoryVoList, categories);
        }

    }
    private CategoryVo category2CategoryVo (Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}