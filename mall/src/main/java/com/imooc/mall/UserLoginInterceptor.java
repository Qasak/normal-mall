package com.imooc.mall;

import com.imooc.mall.exception.UserLoginException;
import com.imooc.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.imooc.mall.consts.MallConsts.CURRENT_USER;

/**
 * @author Wangjs
 * @version 1.0
 * @date 2020/10/30 13:47
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * true表示流程继续，false表示中断
     * 从request中获取session ，看是否有内容
     * 不能返回responseVo.error对象，在中间抛出自定义异常
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandler-> sessionId {}", request.getSession().getId());
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if(user == null) {
            throw new UserLoginException();
        }
//
        return true;
    }
}
