package com.doubles.ex02.service;

import com.doubles.ex02.domain.BoardVO;
import com.doubles.ex02.domain.Criteria;
import com.doubles.ex02.domain.SearchCriteria;
import com.doubles.ex02.persistence.BoardDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDAO;

    @Override
    public void register(BoardVO boardVO) throws Exception {
        boardDAO.create(boardVO);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return boardDAO.read(bno);
    }

    @Override
    public void modify(BoardVO boardVO) throws Exception {
        boardDAO.update(boardVO);
    }

    @Override
    public void remove(Integer bno) throws Exception {
        boardDAO.delete(bno);
    }

    @Override
    public List<BoardVO> list() throws Exception {
        return boardDAO.list();
    }

    @Override
    public List<BoardVO> list(int page) throws Exception {
        return boardDAO.list(page);
    }

    @Override
    public List<BoardVO> list(Criteria criteria) throws Exception {
        return boardDAO.list(criteria);
    }

    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return boardDAO.list(criteria);
    }

    @Override
    public int listCount(Criteria criteria) throws Exception {
        return boardDAO.listCount(criteria);
    }

    @Override
    public int listCount(SearchCriteria criteria) throws Exception {
        return boardDAO.listCount(criteria);
    }
}
