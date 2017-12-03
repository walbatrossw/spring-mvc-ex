package com.doubles.ex01.service;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;
import com.doubles.ex01.domain.SearchCriteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO boardVO) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public void modify(BoardVO boardVO) throws Exception;

    public void remove(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listCriteria(Criteria criteria) throws Exception;

    public int listCountCriteria(Criteria criteria) throws Exception;

    public List<BoardVO> listSearchCriteria(SearchCriteria criteria) throws Exception;

    public int listSearchCount(SearchCriteria criteria) throws Exception;

}
