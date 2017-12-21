package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;

import java.util.Date;

public interface UserDAO {

    // 회원가입 처리
    public void register(UserVO userVO) throws Exception;

    // 로그인 처리
    public UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인 유지
    public void keepLogin(String uid, String sessionId, Date next) throws Exception;

    // sessionKey 확인
    public UserVO checkUserWithSessionKey(String value) throws Exception;

}
