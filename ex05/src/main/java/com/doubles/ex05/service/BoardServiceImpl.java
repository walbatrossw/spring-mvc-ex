package com.doubles.ex05.service;

import com.doubles.ex05.domain.*;
import com.doubles.ex05.persistence.BoardDAO;
import com.doubles.ex05.persistence.ReplyDAO;
import com.doubles.ex05.persistence.UploadDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

    // 게시글 조회 + 조회수 갱신 (30분간 조회수 변동X) - session에 저장된 시간을 통해 조회수 증가 제한
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(Integer bno, HttpSession session) throws Exception {
        // 열람시각
        long updatedTime = 0;
        // session에 열람시각이 존재 할 경우
        if (session.getAttribute("updateTime" + bno) != null) {
            updatedTime = (long) session.getAttribute("updateTime" + bno);
        }
        // 현재시각
        long currentTime = System.currentTimeMillis();
        // 현재시각과 열람시각의 차이가 30분보다 클 경우
        if (currentTime - updatedTime > 60*30*1000) {
            // 조회수 갱신처리
            boardDAO.updateViewCnt(bno);
            // 현재시각을 조회시각으로 저장
            session.setAttribute("updateTime"+bno, currentTime);
        }
        return boardDAO.read(bno);
    }

    // 게시글 수정
    @Transactional
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

    // 게시글 추천하기
    @Override
    public void createLike(BoardLikeVO boardLikeVO) throws Exception {
        boardDAO.createLike(boardLikeVO);
    }
}
