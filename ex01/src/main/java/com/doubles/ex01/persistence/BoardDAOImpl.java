package com.doubles.ex01.persistence;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;
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

    // 게시글 목록 : 전체
    @Override
    public List<BoardVO> listAll() throws Exception {

        return sqlSession.selectList(NAMESPACE + ".listAll");

    }

    // 게시글 목록 : 페이징 (10)
    @Override
    public List<BoardVO> listPage(int page) throws Exception {

        if (page <= 0) {
            page = 1;
        }

        page = (page - 1) * 10;

        return sqlSession.selectList(NAMESPACE + ".listPage", page);

    }

    // 게시글 전체 목록 : 페이징
    @Override
    public List<BoardVO> listCriteria(Criteria criteria) throws Exception {

        return sqlSession.selectList(NAMESPACE + ".listCriteria", criteria);

    }

    // 전체 게시글 갯수
    @Override
    public int countPaging(Criteria criteria) throws Exception {

        return sqlSession.selectOne(NAMESPACE + ".countPaging", criteria);

    }
}
