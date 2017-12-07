package com.doubles.ex03.persistence;

import com.doubles.ex03.domain.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex03.mapper.ReplyMapper";

    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list", bno);
    }

    // 댓글 입력
    @Override
    public void create(ReplyVO replyVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", replyVO);
    }

    // 댓글 수정
    @Override
    public void update(ReplyVO replyVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", replyVO);
    }

    // 댓글 삭제
    @Override
    public void delete(Integer rno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", rno);
    }


}
