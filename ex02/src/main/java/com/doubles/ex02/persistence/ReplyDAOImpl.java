package com.doubles.ex02.persistence;

import com.doubles.ex02.domain.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex02.mapper.ReplyMapper";

    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list", bno);
    }

    @Override
    public void create(ReplyVO replyVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", replyVO);
    }

    @Override
    public void update(ReplyVO replyVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", replyVO);
    }

    @Override
    public void delete(Integer rno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", rno);
    }
}
