package com.doubles.ex03.persistence;

import com.doubles.ex03.domain.BoardVO;
import com.doubles.ex03.domain.Criteria;
import com.doubles.ex03.domain.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex03.mapper.BoardMapper";

    // 입력
    @Override
    public void create(BoardVO boardVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", boardVO);
    }

    // 조회
    @Override
    public BoardVO read(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", bno);
    }

    // 수정
    @Override
    public void update(BoardVO boardVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", boardVO);
    }

    // 삭제
    @Override
    public void delete(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", bno);
    }

    // 목록 : 기본
    @Override
    public List<BoardVO> list() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list");
    }

    // 목록 : 페이징
    @Override
    public List<BoardVO> list(Criteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listPaging", criteria);
    }

    // 목록 전체 갯수
    @Override
    public int listCount(Criteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".listCount", criteria);
    }

    // 목록 : 페이징 + 검색
    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listPaging", criteria);
    }

    // 목록 : 전체 갯수 or 검색된 갯수
    @Override
    public int listCount(SearchCriteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".listCount", criteria);
    }

    // 댓글 갯수
    @Override
    public void updateReplyCnt(Integer bno, int amount) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("amount", amount);

        sqlSession.update(NAMESPACE + ".updateReplyCnt", paramMap);
    }

    // 게시글 조회수 갱신
    @Override
    public void updateViewCnt(Integer bno) throws Exception {
        sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
    }
}
