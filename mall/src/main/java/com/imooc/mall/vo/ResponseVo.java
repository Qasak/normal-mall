package com.imooc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/29 18:06
 */

/**登录：
 * 失败：
 * {
 *     "status": 1,
 *     "msg": "密码错误"
 * }
 *
 * 成功：
 * {
 *     "status": 0,
 *     "data": {
 *         "id": 12,
 *         "username": "aaa",
 *         "email": "aaa@163.com",
 *         "phone": null,
 *         "role": 0,
 *         "createTime": 1479048325000,
 *         "updateTime": 1479048325000
 *     }
 * }
 * 以后可能添加返回商品等内容->使用泛型
 *注册：
 * {
 *     "status": 0,
 *     "msg": "注册成功"
 * }
 *{
 *     "status": 2,
 *     "msg": "用户已存在"
 * }
 * @param <T>
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> ResponseVo<T> success(String msg) {
        return new ResponseVo<>(0, msg);
    }
}
