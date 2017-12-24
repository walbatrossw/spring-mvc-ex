package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.UserMapper";

    // 회원가입 처리
    @Override
    public void register(UserVO userVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".register", userVO);
    }

    // 회원 비밀번호
    @Override
    public UserVO getUser(String uid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".getUser", uid);
    }

    // 회원정보 수정처리
    @Override
    public void updateUser(UserVO userVO) throws Exception {
        sqlSession.update(NAMESPACE + ".updateUser", userVO);
    }

    // 회원비밀번호 수정처리
    @Override
    public void updatePw(UserVO userVO) throws Exception {
        sqlSession.update(NAMESPACE + ".updatePw", userVO);
    }

    // 회원 프로필 사진 수정
    @Override
    public void updateUimage(String uid, String uimage) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("uimage", uimage);
        sqlSession.update(NAMESPACE + ".updateUimage", paramMap);
    }

    // 로그인 처리
    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
    }

    // 로그인 일자 갱신
    @Override
    public void updateLoginDate(String uid) throws Exception {
        sqlSession.update(NAMESPACE + ".updateLoginDate", uid);
    }

    // 로그인 유지
    @Override
    public void keepLogin(String uid, String sessionId, Date next) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        sqlSession.update(NAMESPACE + ".keepLogin", paramMap);
    }

    // Session Key 확인
    @Override
    public UserVO checkUserWithSessionKey(String value) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".checkUserWithSessionKey", value);
    }

}
