package com.doubles.ex02.persistence;

import com.doubles.ex02.domain.BoardVO;

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

}
