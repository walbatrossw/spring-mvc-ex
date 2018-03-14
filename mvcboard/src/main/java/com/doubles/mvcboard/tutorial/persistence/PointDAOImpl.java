package com.doubles.mvcboard.tutorial.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PointDAOImpl implements PointDAO {

    private static final String NAMESPACE = "com.doubles.mvcboard.mappers.tutorial.PointMapper";

    private final SqlSession sqlSession;

    @Inject
    public PointDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void updatePoint(String userId, int point) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("point", point);

        sqlSession.update(NAMESPACE + ".updatePoint", paramMap);

    }
}
