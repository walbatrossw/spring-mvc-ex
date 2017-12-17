package com.doubles.ex05.service;

import com.doubles.ex05.domain.BoardVO;

import java.util.List;

public interface BoardService {

    // 게시글 입력
    public void write(BoardVO boardVO) throws Exception;

    // 게시글 조회
    public BoardVO read(Integer bno) throws Exception;

    // 게시글 수정
    public void modify(BoardVO boardVO) throws Exception;

    // 게시글 삭제
    public void remove(Integer bno) throws Exception;

    // 게시글 목록
    public List<BoardVO> list() throws Exception;

}
