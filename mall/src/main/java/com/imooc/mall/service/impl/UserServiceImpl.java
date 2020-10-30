package com.imooc.mall.service.impl;

import com.imooc.mall.dao.UserMapper;
import com.imooc.mall.enums.RoleEnum;
import com.imooc.mall.pojo.User;
import com.imooc.mall.service.IUserService;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.imooc.mall.enums.ResponseEnum.*;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 16:12
 */
@Service
public class UserServiceImpl implements IUserService {
    /**
     * 注册
     */
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo register(User user) {
        // 设置用户角色，否则数据库报错
        user.setRole(RoleEnum.CUSTOMER.getCode());
        // 用户名不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0 ) {
            return ResponseVo.error(USER_NAME_ALREADY_EXIST);
        }
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0 ) {
            return ResponseVo.error(USER_EMAIL_ALREADY_EXIST);
        }

        // MD5摘要(Spring框架自带)
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)));
        // 写入数据库
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0) {
            return ResponseVo.error(ERROR);
        }
        return ResponseVo.success();
    }
}
