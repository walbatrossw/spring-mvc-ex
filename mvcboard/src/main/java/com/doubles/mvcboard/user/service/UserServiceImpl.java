package com.doubles.mvcboard.user.service;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;
import com.doubles.mvcboard.user.persistence.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
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
    public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
        userDAO.keepLogin(userId, sessionId, sessionLimit);
    }

    @Override
    public UserVO checkLoginBefore(String value) throws Exception {
        return userDAO.checkUserWithSessionKey(value);
    }

    @Override
    public void register(UserVO userVO) throws Exception {
        userDAO.register(userVO);
    }

    @Override
    public boolean isValidUserPw(String userId, String userPw) throws Exception {
        String hashedUserPw = userDAO.getUserPw(userId);

        return BCrypt.checkpw(userPw, hashedUserPw);
    }

    @Override
    public void userInfoModify(UserVO userVO) throws Exception {
        userDAO.userInfoUpdate(userVO);
    }

    @Override
    public void userPwModify(String userId, String newUserPw) throws Exception {
        userDAO.userPwUpdate(userId, newUserPw);
    }

    @Override
    public void userImgModify(String userId, String userImg) throws Exception {
        userDAO.userImgUpdate(userId, userImg);
    }
}
