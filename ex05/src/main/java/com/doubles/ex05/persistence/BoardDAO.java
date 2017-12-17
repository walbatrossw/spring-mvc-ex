package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.BoardVO;
import com.doubles.ex05.domain.Criteria;

import java.util.List;

public interface BoardDAO {

    // 게시글 입력
    public void create(BoardVO boardVO) throws Exception;

    // 게시글 조회
    public BoardVO read(Integer bno) throws Exception;

    // 게시글 수정
    public void update(BoardVO boardVO) throws Exception;

    // 게시글 삭제
    public void delete(Integer bno) throws Exception;

    // 게시글 목록
    public List<BoardVO> list() throws Exception;

    // 게시글 목록 + 페이징
    public List<BoardVO> list(Criteria criteria) throws Exception;

    // 게시글 전체 갯수
    public int countList(Criteria criteria) throws Exception;
}
