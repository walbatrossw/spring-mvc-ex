package com.doubles.mvcboard.tutorial.persistence;

import com.doubles.mvcboard.tutorial.domain.MessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private static String NAMESPACE = "com.doubles.mvcboard.mappers.tutorial.MessageMapper";

    private final SqlSession sqlSession;

    @Inject
    public MessageDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void create(MessageVO messageVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", messageVO);
    }

    @Override
    public MessageVO readMessage(Integer messageNo) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".readMessage", messageNo);
    }

    @Override
    public void updateState(Integer messageNo) throws Exception {
        sqlSession.update(NAMESPACE + ".updateState", messageNo);
    }
}
