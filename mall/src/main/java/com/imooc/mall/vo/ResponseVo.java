package com.imooc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.mall.enums.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

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
    //data可要可不要，通过@JsonInclude(value = JsonInclude.Include.NON_NULL)注解，当其为null时，直接不返回
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
    public static <T> ResponseVo<T> successByMsg(String msg) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), msg);
    }
    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getName());
    }
    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), data);
    }
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
        return new ResponseVo<>(responseEnum.getCode(), responseEnum.getName());
    }
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg) {
        return new ResponseVo<>(responseEnum.getCode(), msg);
    }
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new ResponseVo<>(responseEnum.getCode(), bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage());
    }

}
