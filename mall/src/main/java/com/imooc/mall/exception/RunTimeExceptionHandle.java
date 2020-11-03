package com.imooc.mall.exception;

import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

import static com.imooc.mall.enums.ResponseEnum.ERROR;
import static com.imooc.mall.enums.ResponseEnum.NEED_LOGIN;

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
    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginHandle() {
        return ResponseVo.error(NEED_LOGIN);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError());
        return ResponseVo.error(ResponseEnum.PARAM_ERROR,
                bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }

}
