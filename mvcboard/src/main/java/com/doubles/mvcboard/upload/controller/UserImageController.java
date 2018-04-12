package com.doubles.mvcboard.upload.controller;

import com.doubles.mvcboard.commons.util.UploadFileUtils;
import com.doubles.mvcboard.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/img")
public class UserImageController {

    private final UserService userService;

    @Inject
    public UserImageController(UserService userService) {
        this.userService = userService;
    }

    // 회원 프로필 사진 추가
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String userImgUpload(String userId, MultipartFile userImgFile, HttpServletRequest request, HttpSession httpSession) throws Exception {

        // 회원 프로필 사진파일 업로드 처리
        String savedUploadPath = UploadFileUtils.uploadUserImg(userImgFile, request);
        // 회원 프로필 사진명 업데이트
        userService.userImgModify(userId, savedUploadPath);


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
