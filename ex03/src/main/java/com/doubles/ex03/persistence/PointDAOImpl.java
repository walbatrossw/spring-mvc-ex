package com.doubles.ex03.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PointDAOImpl implements PointDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex03.mapper.PointMapper";

    @Override
    public void updatePoint(String uid, int point) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("point", point);

        sqlSession.update(NAMESPACE + ".updatePoint", paramMap);

    }

}
