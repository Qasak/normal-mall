package com.imooc.mall.service;

import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;

import java.util.List;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 20:22
 */
public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();
}
