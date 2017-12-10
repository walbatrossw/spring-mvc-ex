package com.doubles.ex04.persistence;

import com.doubles.ex04.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
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

    // 게시글 목록 + 페이징 + 검색

}
