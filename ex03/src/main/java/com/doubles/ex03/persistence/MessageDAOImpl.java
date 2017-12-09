package com.doubles.ex03.persistence;

import com.doubles.ex03.domain.MessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex03.mapper.MessageMapper";

    @Override
    public void create(MessageVO messageVO) throws Exception {

        sqlSession.insert(NAMESPACE + ".create", messageVO);

    }

    @Override
    public MessageVO readMessage(Integer mno) throws Exception {

        return sqlSession.selectOne(NAMESPACE + ".readMessage", mno);

    }

    @Override
    public void updateState(Integer mno) throws Exception {

        sqlSession.update(NAMESPACE + ".updateState", mno);

    }
}
