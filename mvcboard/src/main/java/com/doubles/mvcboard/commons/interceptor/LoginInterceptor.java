package com.doubles.mvcboard.commons.interceptor;

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
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO =  modelMap.get("user");

        if (userVO != null) {
            logger.info("new login success");
            httpSession.setAttribute(LOGIN, userVO);
            response.sendRedirect("/");
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            httpSession.removeAttribute(LOGIN);
        }

        return true;
    }
}
