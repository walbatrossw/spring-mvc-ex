package com.doubles.mvcboard.user.persistence;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

import java.util.Date;

public interface UserDAO {

    // 로그인 처리
    UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인 유지 처리
    void keepLogin(String userId, String sessionId, Date next) throws Exception;

    UserVO checkUserWithSessionKey(String value) throws Exception;

    // 회원가입 처리
    void register(UserVO userVO) throws Exception;
}
