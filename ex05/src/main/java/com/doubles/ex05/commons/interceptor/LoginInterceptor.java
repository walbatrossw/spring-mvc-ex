package com.doubles.ex05.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    // HttpSession 보관처리
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO = modelMap.get("userVO");
        if (userVO != null) {
            logger.info("new login success");
            session.setAttribute(LOGIN, userVO);
            //response.sendRedirect("/");
            Object destination = session.getAttribute("destination");
            response.sendRedirect(destination != null ? (String) destination : "/");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            session.removeAttribute(LOGIN);
        }
        return true;
    }
}
