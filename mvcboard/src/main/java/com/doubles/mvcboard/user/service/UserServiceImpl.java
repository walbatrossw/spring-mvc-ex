package com.doubles.mvcboard.user.service;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;
import com.doubles.mvcboard.user.persistence.UserDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Inject
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        return userDAO.login(loginDTO);
    }

    @Override
    public void keepLogin(String userId, String sessionId, Date next) throws Exception {
        userDAO.keepLogin(userId, sessionId, next);
    }

    @Override
    public UserVO checkLoginBefore(String value) throws Exception {
        return userDAO.checkUserWithSessionKey(value);
    }

    @Override
    public void register(UserVO userVO) throws Exception {
        userDAO.register(userVO);
    }
}
