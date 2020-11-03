package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.vo.ProductDetailVo;
import com.imooc.mall.vo.ResponseVo;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/31 17:21
 */
public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);
    ResponseVo<ProductDetailVo> detail(Integer productId);
}
