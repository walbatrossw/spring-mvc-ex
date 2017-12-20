package com.doubles.ex05.commons.interceptor;

import com.doubles.ex05.domain.UserVO;
import com.doubles.ex05.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Inject
    private UserService userService;

    private void saveDestination(HttpServletRequest request) {

        String uri = request.getRequestURI();

        String query = request.getQueryString();

        if (query == null || query.equals("null")) {
            query =  "";
        } else {
            query = "?" + query;
        }

        if (request.getMethod().equals("GET")) {
            logger.info("destination : " + (uri + query));
            request.getSession().setAttribute("destination", uri + query);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {
            logger.info("current user is not logged");
            saveDestination(request);
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
            if (loginCookie != null) {
                UserVO userVO = userService.checkLoginBefore(loginCookie.getValue());
                logger.info("UserVO : " + userVO);
                if (userVO != null) {
                    session.setAttribute("login", userVO);
                    return true;
                }
            }
            response.sendRedirect("/user/login");
            return false;
        }

        return true;
    }

}
