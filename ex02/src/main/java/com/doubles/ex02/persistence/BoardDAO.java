package com.doubles.ex02.persistence;

import com.doubles.ex02.domain.BoardVO;
import com.doubles.ex02.domain.Criteria;

import java.util.List;

public interface BoardDAO {

    // 입력
    public void create(BoardVO boardVO) throws Exception;

    // 조회
    public BoardVO read(Integer bno) throws Exception;

    // 수정
    public void update(BoardVO boardVO) throws Exception;

    // 삭제
    public void delete(Integer bno)  throws Exception;

    // 목록
    public List<BoardVO> list() throws Exception;

    // 목록 페이징 1
    public List<BoardVO> list(int page) throws Exception;

    // 목록 페이징 2
    public List<BoardVO> list(Criteria criteria) throws Exception;

    // 전체 게시글의 갯수
    public int listCount(Criteria criteria) throws Exception;

}
