package com.doubles.ex01.service;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.persistence.BoardDAO;
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
    public void delete(Integer bno) throws Exception {
        boardDAO.delete(bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return boardDAO.listAll();
    }
}
