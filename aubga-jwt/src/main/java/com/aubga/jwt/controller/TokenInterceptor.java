package com.aubga.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aubga.jwt.util.TokenUtil;

/**
 * 自定义token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("admin-token");
        if (token != null){
            boolean result = TokenUtil.verify(token);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        System.out.println("认证失败");
        response.getWriter().write("50000");
        return false;
    }
}