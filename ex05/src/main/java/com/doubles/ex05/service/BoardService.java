package com.doubles.ex05.service;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.BoardVO;
import com.doubles.ex05.domain.Criteria;
import com.doubles.ex05.domain.SearchCriteria;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {

    // 게시글 입력
    public void write(BoardVO boardVO) throws Exception;

    // 게시글 조회
    public BoardVO read(Integer bno, HttpSession session) throws Exception;

    // 게시글 수정
    public void modify(BoardVO boardVO) throws Exception;

    // 게시글 삭제
    public void remove(Integer bno) throws Exception;

    // 게시글 목록
    public List<BoardVO> list() throws Exception;

    // 게시글 목록 + 페이징
    public List<BoardVO> list(Criteria criteria) throws Exception;

    // 게시글 전체 갯수
    public int listCount(Criteria criteria) throws Exception;

    // 게시글 목록 + 페이징
    public List<BoardVO> list(SearchCriteria criteria) throws Exception;

    // 게시글 전체 갯수
    public int searchedListCount(SearchCriteria criteria) throws Exception;

    // 회원이 작성한 게시글 목록
    public List<BoardVO> userBoardList(String uid) throws Exception;
}
