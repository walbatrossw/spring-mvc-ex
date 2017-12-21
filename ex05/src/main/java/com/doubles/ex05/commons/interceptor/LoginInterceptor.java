package com.doubles.ex05.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    // 로그인 처리 후 session 정보 보관
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        // session 값
        HttpSession session = request.getSession();
        // model에 저장된 값을 userVO에 저장
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO = modelMap.get("userVO");
        if (userVO != null) {
            logger.info("new login success");
            // session에 로그인한 사용자 정보를 저장
            session.setAttribute(LOGIN, userVO);
            // 로그인 유지값이 존재하면
            if (request.getParameter("useCookie") != null) {
                logger.info("remember me....");
                // 로그인 쿠키 객체 생성
                Cookie loginCookie = new Cookie("loginCookie", session.getId());
                // 모든 경로에서 접근 가능하게 처리
                loginCookie.setPath("/");
                // 쿠키 유효 기간
                loginCookie.setMaxAge(60 * 60 * 24 * 7);
                // 쿠키 저장
                response.addCookie(loginCookie);
            }
            //response.sendRedirect("/");
            // 로그인 페이지 접근 전의 페이지
            Object destination = session.getAttribute("destination");
            // 삼항 연산자로 이전페이지가 존재하지 않으면 메인페이지로 이동
            response.sendRedirect(destination != null ? (String) destination : "/");
        }
    }

    // 기존의 session 정보 초기화
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session 값
        HttpSession session = request.getSession();
        // 기존 session login 값이 존재하면
        if (session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            // 삭제
            session.removeAttribute(LOGIN);
        }
        return true;
    }
}
