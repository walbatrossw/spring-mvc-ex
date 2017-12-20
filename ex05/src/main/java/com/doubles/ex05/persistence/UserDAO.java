package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;

public interface UserDAO {

    public UserVO login(LoginDTO loginDTO) throws Exception;

}
