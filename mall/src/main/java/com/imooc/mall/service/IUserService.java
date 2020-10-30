package com.imooc.mall.service;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 15:48
 */

import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;

/**
 *注册/登录
 */
public interface IUserService {
    // 注册
    ResponseVo register(User user);

}
