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

    // 로그인처리
    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
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
