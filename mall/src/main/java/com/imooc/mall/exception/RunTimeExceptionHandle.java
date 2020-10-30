package com.imooc.mall.exception;

import com.imooc.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.imooc.mall.enums.ResponseEnum.ERROR;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 11:48
 */
@ControllerAdvice
public class RunTimeExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ERROR, e.getMessage());
    }
}