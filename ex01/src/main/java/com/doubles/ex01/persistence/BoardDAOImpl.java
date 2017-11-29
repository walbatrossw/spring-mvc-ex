package com.doubles.ex01.persistence;

import com.doubles.ex01.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex01.mapper.BoardMapper";

    // 게시글 입력
    @Override
    public void create(BoardVO boardVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", boardVO);
    }

    // 게시글 조회
    @Override
    public BoardVO read(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", bno);
    }

    // 게시글 수정
    @Override
    public void update(BoardVO vo) throws Exception {
        sqlSession.update(NAMESPACE + ".update", vo);
    }

    // 게시글 삭제
    @Override
    public void delete(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", bno);
    }

    // 게시글 전체 목록
    @Override
    public List<BoardVO> listAll() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listAll");
    }

}
