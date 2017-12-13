package com.doubles.ex04.service;

import com.doubles.ex04.domain.BoardVO;
import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.SearchCriteria;
import com.doubles.ex04.persistence.BoardDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDAO;

    // 게시글 입력 처리
    @Transactional
    @Override
    public void write(BoardVO boardVO) throws Exception {
        boardDAO.create(boardVO);
        String[] files = boardVO.getFiles();
        if (files == null) {
            return;
        }
        for (String fileName : files) {
            boardDAO.addAttach(fileName);
        }
    }

    // 게시글 조회 + 조회수 갱신
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(Integer bno) throws Exception {
        boardDAO.updateViewCnt(bno);
        return boardDAO.read(bno);
    }

    // 게시글 수정 처리
    @Override
    public void modify(BoardVO boardVO) throws Exception {
        boardDAO.update(boardVO);
    }

    // 게시글 삭제
    @Override
    public void remove(Integer bno) throws Exception {
        boardDAO.delete(bno);
    }

    // 게시글 목록
    @Override
    public List<BoardVO> list() throws Exception {
        return boardDAO.list();
    }

    // 게시글 목록 + 페이징
    @Override
    public List<BoardVO> list(Criteria criteria) throws Exception {
        return boardDAO.list(criteria);
    }

    // 게시글의 전체 갯수
    @Override
    public int listCount(Criteria criteria) throws Exception {
        return boardDAO.listCount(criteria);
    }

    // 게시글 목록 + 페이징 + 검색
    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return boardDAO.list(criteria);
    }

    // 게시글 전체 갯수 or 검색된 게시글의 수
    @Override
    public int listSearchCount(SearchCriteria criteria) throws Exception {
        return boardDAO.listSearchCount(criteria);
    }


}
