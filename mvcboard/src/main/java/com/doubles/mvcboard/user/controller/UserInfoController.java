package com.doubles.mvcboard.user.controller;

import com.doubles.mvcboard.user.domain.UserVO;
import com.doubles.mvcboard.user.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/info")
public class UserInfoController {

    private final UserService userService;

    @Inject
    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    // 회원 정보 페이지
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userInfo() throws Exception {

        return "/user/info";
    }

    // 회원 정보 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String userInfoModify(UserVO userVO, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        boolean userPwValid = userService.isValidUserPw(userVO.getUserId(), userVO.getUserPw());
        if (!userPwValid) {
            redirectAttributes.addFlashAttribute("msg", "INVALID userPw");
            return "redirect:/user/info";
        }
        userService.userInfoModify(userVO);
        httpSession.setAttribute("login", userVO);
        redirectAttributes.addFlashAttribute("msg", "MODIFIED userInfo");
        return "redirect:/user/info";
    }

    // 회원 비밀번호 변경
    @RequestMapping(value = "/password/modify", method = RequestMethod.POST)
    public String userPwModify(String userId, String oldUserPw, String newUserPw, RedirectAttributes redirectAttributes) throws Exception {

        // 비밀번호 일치 확인
        if (!userService.isValidUserPw(userId, oldUserPw)) {
            redirectAttributes.addFlashAttribute("msg", "INVALID userPw");
            return "redirect:/user/info";
        }

        // 새로운 비밀번호 == 현재 비밀번호
        if (userService.isValidUserPw(userId, newUserPw)) {
            redirectAttributes.addFlashAttribute("msg", "SAME userPw");
            return "redirect:/user/info";
        }

        // 새로운 비밀번호 암호화 처리, 변경
        String hashedPw = BCrypt.hashpw(newUserPw, BCrypt.gensalt());
        userService.userPwModify(userId, hashedPw);
        redirectAttributes.addFlashAttribute("msg", "MODIFIED userPw");

        return "redirect:/user/info";
    }

}
