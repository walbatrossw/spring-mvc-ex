package com.doubles.ex05.service;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;

import java.util.Date;

public interface UserService {

    // 회원가입 처리
    public void register(UserVO userVO) throws Exception;

    // 회원정보 수정처리
    public void modifyUser(UserVO userVO) throws Exception;

    // 회원 정보
    public UserVO getUser(String uid) throws Exception;

    // 회원비밀번호 수정처리
    public void modifyPw(UserVO userVO) throws Exception;

    // 회원 프로필 사진 수정
    public void modifyUimage(String uid, String uimage) throws Exception;

    // 로그인 처리
    public UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인유지
    public void keepLogin(String uid, String sessionId, Date next) throws Exception;

    // Session Key 확인
    public UserVO checkLoginBefore(String value) throws Exception;
}
