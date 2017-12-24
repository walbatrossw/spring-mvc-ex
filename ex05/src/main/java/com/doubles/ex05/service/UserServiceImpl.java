package com.doubles.ex05.service;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import com.doubles.ex05.persistence.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    // 회원가입 처리
    @Override
    public void register(UserVO userVO) throws Exception {
        userDAO.register(userVO);
    }

    // 회원 비밀번호
    @Override
    public UserVO getUser(String uid) throws Exception {
        return userDAO.getUser(uid);
    }

    // 회원정보 수정처리
    @Override
    public void modifyUser(UserVO userVO) throws Exception {
        userDAO.updateUser(userVO);
    }

    // 회원비밀번호 수정처리
    @Override
    public void modifyPw(UserVO userVO) throws Exception {
        userDAO.updatePw(userVO);
    }

    // 회원 프로필 사진 수정
    @Override
    public void modifyUimage(String uid, String uimage) throws Exception {
        userDAO.updateUimage(uid, uimage);
    }

    // 로그인 처리
    @Transactional
    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        userDAO.updateLoginDate(loginDTO.getUid());
        return userDAO.login(loginDTO);
    }

    // 로그인 유지
    @Override
    public void keepLogin(String uid, String sessionId, Date next) throws Exception {
        userDAO.keepLogin(uid, sessionId, next);
    }

    // Session Key 확인
    @Override
    public UserVO checkLoginBefore(String value) throws Exception {
        return userDAO.checkUserWithSessionKey(value);
    }

}
