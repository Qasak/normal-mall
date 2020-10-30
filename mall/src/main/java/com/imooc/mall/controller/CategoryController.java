package com.imooc.mall.controller;

import com.imooc.mall.service.impl.CategoryServiceImpl;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 17:42
 */
@RestController
@RequestMapping
@Slf4j
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll() {
        return categoryService.selectAll();
    }
}
