package com.doubles.ex01.persistence;

import com.doubles.ex01.domain.BoardVO;

import java.util.List;

public interface BoardDAO {

    public void create(BoardVO boardVO) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public void update(BoardVO vo) throws Exception;

    public void delete(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

}
