package com.doubles.ex05.commons.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class SampleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle....");
        Object result = modelAndView.getModel().get("result");
        if (result != null) {
            request.getSession().setAttribute("result", result);
            response.sendRedirect("/doA");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("Pre Handle...");
        HandlerMethod method = (HandlerMethod) handler;
        Method methodObj = method.getMethod();
        System.out.println("Bean : " + method.getBean());
        System.out.println("Method : " + methodObj);
        return true;
    }
}
