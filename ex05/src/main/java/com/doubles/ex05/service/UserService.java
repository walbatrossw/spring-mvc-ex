package com.doubles.ex05.service;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;

public interface UserService {

    public UserVO login(LoginDTO loginDTO) throws Exception;

}
