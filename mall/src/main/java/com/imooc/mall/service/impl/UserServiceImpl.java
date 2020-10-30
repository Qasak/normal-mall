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
    public ResponseVo<User> register(User user) {
        // 设置用户角色，否则数据库报错
        user.setRole(RoleEnum.CUSTOMER.getCode());
        // 用户名不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0 ) {
            return ResponseVo.error(USERNAME_ALREADY_EXIST);
        }
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0 ) {
            return ResponseVo.error(EMAIL_ALREADY_EXIST);
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
    @Override
    public ResponseVo login(String username, String password) {
        // 数据库中username有索引，只用一个字段查
        User user = userMapper.selectByUsername(username);
        if(user == null) {
            //用户不存在： 用户名或密码错误
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
            // 密码错误： 用户名或密码错误
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }
        // 不要向前端返回用户密码
        user.setPassword("");
        return ResponseVo.success(user);

    }
}
