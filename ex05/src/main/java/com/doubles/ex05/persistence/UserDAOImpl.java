package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
    }
}
