package com.doubles.ex01.persistence;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;

import java.util.List;

public interface BoardDAO {

    public void create(BoardVO boardVO) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public void update(BoardVO vo) throws Exception;

    public void delete(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listPage(int page) throws Exception;

    public List<BoardVO> listCriteria(Criteria criteria) throws Exception;

    public int countPaging(Criteria criteria) throws Exception;

}
