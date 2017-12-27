package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.ReplyLikeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LikeDAOImpl implements LikeDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.LikeMapper";

    @Override
    public void createBoardLike(BoardLikeVO boardLikeVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".createBoardLike", boardLikeVO);
    }

    @Override
    public void deleteBoardLike(BoardLikeVO boardLikeVO) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteBoardLike", boardLikeVO);
    }

    @Override
    public int countBoardLikes(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countBoardLikes", bno);
    }

    @Override
    public boolean checkBoardLike(Integer bno, String uid) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("uid", uid);

        return sqlSession.selectOne(NAMESPACE + ".checkBoardLike", paramMap);
    }

    @Override
    public void createReplyLike(ReplyLikeVO replyLikeVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".createReplyLike", replyLikeVO);
    }

    @Override
    public void deleteReplyLike(ReplyLikeVO replyLikeVO) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteReplyLike", replyLikeVO);
    }

    @Override
    public int countReplyLikes(Integer rno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countReplyLikes", rno);
    }

    @Override
    public boolean checkReplyLike(Integer rno, String uid) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("rno", rno);
        paramMap.put("uid", uid);

        return sqlSession.selectOne(NAMESPACE + ".checkReplyLike", paramMap);
    }
}
