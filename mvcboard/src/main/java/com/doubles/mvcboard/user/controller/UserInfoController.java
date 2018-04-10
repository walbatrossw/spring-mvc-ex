package com.doubles.mvcboard.user.controller;

import com.doubles.mvcboard.user.domain.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {

    // 회원 정보, 프로필 페이지
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView userInfo() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/info");

        return modelAndView;
    }

    // 회원정보 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<String> userInfoModify(@RequestBody UserVO userVO) throws Exception {
        ResponseEntity<String> entity = null;
        try {
            // 회원 정보 수정처리
            System.out.println(userVO);
            entity = new ResponseEntity<>("MODIFIED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 회원비밀번호 수정

    // 회원 프로필 사진 수정

}
