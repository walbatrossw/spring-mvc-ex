package com.doubles.ex05.service;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;

import java.util.Date;

public interface UserService {

    // 로그인 처리
    public UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인유지
    public void keepLogin(String uid, String sessionId, Date next) throws Exception;

    // Session Key 확인
    public UserVO checkLoginBefore(String value) throws Exception;
}
