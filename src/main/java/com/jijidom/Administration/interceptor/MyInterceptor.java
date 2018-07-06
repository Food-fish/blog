package com.jijidom.Administration.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 9:11 2018/5/28
 * @Description:
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
        System.out.println("MyInterceptor01--->preHandle()执行控制器之前调用此方法....");
        //返回true则放行，返回false则将其拦截住
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView arg3) throws Exception {
        System.out.println("MyInterceptor01--->postHandle()执行控制器之后且在渲染视图前调用此方法....");
    }
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object arg2, Exception arg3) throws Exception {
        System.out.println("MyInterceptor01--->afterCompletion()执行控制器之后且在完成渲染视图后调用此方法....");
    }
}
