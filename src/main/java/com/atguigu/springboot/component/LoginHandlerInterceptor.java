package com.atguigu.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
登录检查---过滤器拦截器

* */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /*alter + insert 快捷键可以插入未实现的方法*/


    /*方法调用之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginEmailAddress");
        if(user==null){
            //未登录，返回登录页面----注意这里就是前端设置的message
            request.setAttribute("message","没有权限请先登录");
            /*请求转发*/
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
