package com.doubles.ex05.service;

import com.doubles.ex05.domain.BoardVO;
import com.doubles.ex05.domain.Criteria;
import com.doubles.ex05.domain.ReplyVO;
import com.doubles.ex05.domain.SearchCriteria;
import com.doubles.ex05.persistence.BoardDAO;
import com.doubles.ex05.persistence.ReplyDAO;
import com.doubles.ex05.persistence.UploadDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDAO;

    @Inject
    private UploadDAO uploadDAO;

    // 게시글 입력
    @Transactional
    @Override
    public void write(BoardVO boardVO) throws Exception {
        String[] files = boardVO.getFiles();
        if (files == null) {
            boardDAO.create(boardVO);
            return;
        }
        boardVO.setAttachcnt(files.length);
        boardDAO.create(boardVO);
        Integer bno = boardVO.getBno();
        for (String fileName : files) {
            uploadDAO.addAttach(fileName, bno);
        }
    }

    // 게시글 조회 + 조회수 갱신
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(Integer bno) throws Exception {
        boardDAO.updateViewCnt(bno);
        return boardDAO.read(bno);
    }

    // 게시글 수정
    @Override
    public void modify(BoardVO boardVO) throws Exception {
        int bno = boardVO.getBno();
        uploadDAO.deleteAllAttach(bno);

        String[] files = boardVO.getFiles();
        if (files == null) {
            boardVO.setAttachcnt(0);
            boardDAO.update(boardVO);
            return;
        }

        boardVO.setAttachcnt(files.length);
        boardDAO.update(boardVO);
        for (String fileName : files) {
            uploadDAO.replaceAttach(fileName, bno);
        }
    }

    // 게시글 삭제
    @Override
    public void remove(Integer bno) throws Exception {
        uploadDAO.deleteAllAttach(bno);
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

    // 게시글 전체 갯수
    @Override
    public int listCount(Criteria criteria) throws Exception {
        return boardDAO.countList(criteria);
    }

    // 게시글 목록 + 페이징 + 검색
    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return boardDAO.list(criteria);
    }

    // 게시글 전체 갯수 or 검색된 게시글 갯수
    @Override
    public int searchedListCount(SearchCriteria criteria) throws Exception {
        return boardDAO.countSearchedList(criteria);
    }
}
