package com.doubles.mvcboard.user.persistence;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

public interface UserDAO {

    public UserVO login(LoginDTO loginDTO) throws Exception;

}
