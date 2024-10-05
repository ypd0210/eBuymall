package com.study.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.study.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: eBuymall
 * @description: 自定义拦截器，将用户信息写入到ThreadLocal中
 * @author: 心念
 * @create: 2024-09-04 14:46
 **/
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先获取用户信息
        String userInfo = request.getHeader("userInfo");
        //判断是否拿到用户信息
        if(StrUtil.isNotBlank(userInfo)){
            UserContext.setUser(Long.valueOf(userInfo));
        }else{
            System.out.println("userInfo is Null or Empty");
        }
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }
}
