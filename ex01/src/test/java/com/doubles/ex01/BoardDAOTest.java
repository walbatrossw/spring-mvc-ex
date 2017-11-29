package com.doubles.ex01;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class BoardDAOTest {

    @Inject
    private BoardDAO boardDAO;

    private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

    @Test
    public void testCreate() throws Exception {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새글입니다...");
        boardVO.setContent("새글 내용입니다...");
        boardVO.setWriter("user00");
        boardDAO.create(boardVO);
    }

    @Test
    public void testRead() throws Exception {
        logger.info(boardDAO.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception {
        BoardVO boardVO = new BoardVO();
        boardVO.setBno(1);
        boardVO.setTitle("수정된 글입니다...");
        boardVO.setContent("수정된 글 내용입니다...");
        boardDAO.update(boardVO);
    }

    @Test
    public void testDelete() throws Exception {
        boardDAO.delete(1);
    }


}
