package com.doubles.ex05.service;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import com.doubles.ex05.persistence.UserDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        return userDAO.login(loginDTO);
    }
}
