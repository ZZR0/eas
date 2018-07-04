package com.example.eas.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//对没有权限的操作进行了限制
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //判断有无操作权限
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("LoginUser");
        if (user == null){
            //未登录，返回false
            httpServletRequest.setAttribute("msg","没有权限，请先登录");
            httpServletRequest.getRequestDispatcher("index.html").forward(httpServletRequest,httpServletResponse);
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
