package com.doubles.mvcboard.user.service;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

public interface UserService {

    public UserVO login(LoginDTO loginDTO) throws Exception;

}
