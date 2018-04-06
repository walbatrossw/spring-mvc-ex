package com.doubles.mvcboard.tutorial.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class SampleInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("post handle....");
        Object result = modelAndView.getModel().get("result");
        if (result != null) {
            request.getSession().setAttribute("result", result);
            response.sendRedirect("/interceptor/doA");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("pre handle...");

        HandlerMethod method = (HandlerMethod) handler;
        Method methodObj = method.getMethod();

        logger.info("Bean : " + method.getBean());
        logger.info("Method : " + methodObj);

        return true;
    }
}
