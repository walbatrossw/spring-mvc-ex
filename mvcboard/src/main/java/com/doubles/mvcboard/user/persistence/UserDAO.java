package com.doubles.mvcboard.user.persistence;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

import java.util.Date;

public interface UserDAO {

    // 로그인 처리
    UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인 유지 처리
    void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;

    // 세션키 검증
    UserVO checkUserWithSessionKey(String value) throws Exception;

    // 회원가입 처리
    void register(UserVO userVO) throws Exception;

    // 회원비밀번호
    String getUserPw(String userId) throws Exception;

    // 회원정보 수정처리
    void userInfoUpdate(UserVO userVO) throws Exception;

    // 회원 비밀번호 수정
    void userPwUpdate(String userId, String newUserPw) throws Exception;

    // 회원 프로필 사진파일 수정
    void userImgUpdate(String userId, String userImg) throws Exception;

}
