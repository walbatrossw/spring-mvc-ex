package com.doubles.mvcboard.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/img")
public class UserImageController {

    // 회원 프로필 사진 추가
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String userImgUpload(String userId, MultipartFile userImgFile, HttpServletRequest request) throws Exception {
        // 회원 프로필 사진파일 업로드 처리
        // 회원 프로필 사진명 업데이트
        return "redirect:/user/info";
    }

    // 회원 프로필 사진 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String userImgDelete(String userId, HttpServletRequest request) throws Exception {
        // 회원 프로필 사진파일 삭제 처리
        // 회원 프로필 사진명 기본값 변경
        return "redirect:/user/info";
    }

    // 회원 프로필 사진 변경
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String userImgModify(String userId, MultipartFile userImgFile, HttpServletRequest request) throws Exception {
        // 회원 프로필 사진파일 삭제처리
        // 회원 프로필 사진파일 업로드 처리
        // 회원 프로필 사진명 업데이트
        return "redirect:/user/info";
    }


}
