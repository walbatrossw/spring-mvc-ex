package com.doubles.ex04.persistence;

import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex04.mapper.ReplyMapper";

    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list", bno);
    }

    // 댓글 목록 + 페이징
    @Override
    public List<ReplyVO> list(Integer bno, Criteria criteria) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("criteria", criteria);

        return sqlSession.selectList(NAMESPACE + ".listPaging", paramMap);

    }

    // 댓글 갯수
    @Override
    public int listCount(Integer bno) throws Exception {

        return sqlSession.selectOne(NAMESPACE + ".listCount", bno);

    }

    // 댓글 추가
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

    // 특정 댓글의 게시글의 번호
    @Override
    public int getBno(Integer rno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".getBno", rno);
    }
}
