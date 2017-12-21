package com.doubles.ex05.controller;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import com.doubles.ex05.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    // 회원가입 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() {
        return "user/register";
    }

    // 회원가입 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
        userService.register(userVO);
        redirectAttributes.addFlashAttribute("msg", "REGISTERED");
        return "redirect:/user/login";
    }

    // 로그인 페이지
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
        return "user/login";
    }

    // 로그인 처리
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public void loginPOST(LoginDTO loginDTO,
                          HttpSession session,
                          Model model) throws Exception {
        // 아이디 비번 일치 확인
        UserVO userVO = userService.login(loginDTO);
        // 불일치할 경우
        if (userVO == null) {
            return;
        }
        // 일치할 경우
        model.addAttribute("userVO", userVO);
        // 로그인 유지를 선택한 경우
        if (loginDTO.isUseCookie()) {
            // 로그인 유지 유효기간 : 일주일
            int amount = 60 * 60 * 24 * 7;
            // 로그인 유지 유효 일자
            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
            // 로그인 유지 기간 갱신(아이디, 세션아이디, 유효일자)
            userService.keepLogin(userVO.getUid(), session.getId(), sessionLimit);
        }
    }

    // 로그아웃 처리
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session) throws Exception {
        // session에 저장된 login 값
        Object object = session.getAttribute("login");
        if (object != null) {
            UserVO userVO = (UserVO) object;
            // session 정보 삭제
            session.removeAttribute("login");
            // session 초기화
            session.invalidate();
            // 로그인 쿠키값
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
            if (loginCookie != null) {
                loginCookie.setPath("/");
                // 쿠키 유효기간 0
                loginCookie.setMaxAge(0);
                // 쿠키 저장
                response.addCookie(loginCookie);
                // 로그인 유지 갱신
                userService.keepLogin(userVO.getUid(), session.getId(), new Date());
            }
        }

        return "user/logout";
    }
}
