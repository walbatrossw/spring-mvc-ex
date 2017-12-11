package com.doubles.ex04.persistence;

import com.doubles.ex04.domain.BoardVO;
import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.print.attribute.standard.MediaSize;
import java.util.List;


@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex04.mapper.BoardMapper";

    // 게시글 입력 처리
    @Override
    public void create(BoardVO boardVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", boardVO);
    }

    // 게시글 조회
    @Override
    public BoardVO read(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", bno);
    }

    // 게시글 수정 처리
    @Override
    public void update(BoardVO boardVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", boardVO);
    }

    // 게시글 삭제
    @Override
    public void delete(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", bno);
    }

    // 게시글 목록
    @Override
    public List<BoardVO> list() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list");
    }

    // 게시글 목록 + 페이징
    @Override
    public List<BoardVO> list(Criteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listPaging", criteria);
    }

    // 게시글의 전체 갯수
    @Override
    public int listCount(Criteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".listCount", criteria);
    }

    // 게시글 목록 + 페이징 + 검색

    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listSearchPaging", criteria);
    }

    // 게시글 전체 갯수 or 검색된 게시글의 수
    @Override
    public int listSearchCount(SearchCriteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".listSearchCount", criteria);
    }


}
