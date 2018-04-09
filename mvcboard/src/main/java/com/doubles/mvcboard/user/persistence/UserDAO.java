package com.doubles.mvcboard.user.persistence;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

import java.util.Date;

public interface UserDAO {

    UserVO login(LoginDTO loginDTO) throws Exception;

    void keepLogin(String userId, String sessionId, Date next);

    UserVO checkUserWithSessionKey(String value);

}