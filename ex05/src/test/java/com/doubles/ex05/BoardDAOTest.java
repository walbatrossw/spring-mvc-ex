package com.doubles.ex05;

import com.doubles.ex05.domain.BoardVO;
import com.doubles.ex05.persistence.BoardDAO;
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

    // 게시글 입력 테스트, 더미데이터 입력
    @Test
    public void testCreate() throws Exception {

        for (int i = 1; i <= 1000; i++) {
            BoardVO boardVO = new BoardVO();
            boardVO.setTitle(i + "번째 글제목입니다...");
            boardVO.setContent(i + "번째 글 내용입니다.......");
            boardVO.setWriter("user0"+(i%10));
            boardDAO.create(boardVO);
        }

    }

    // 게시글 조회 테스트
    @Test
    public void testRead() throws Exception {

        logger.info(boardDAO.read(1).toString());

    }

    // 게시글 수정 테스트
    @Test
    public void testUpdate() throws Exception {

        BoardVO boardVO = new BoardVO();
        boardVO.setBno(1000);
        boardVO.setTitle(1000 + "번째 글 수정한 제목입니다...");
        boardVO.setContent(1000 + "번째 글 수정한 내용입니다.......");
        boardDAO.update(boardVO);

    }

    // 게시글 삭제 테스트
    @Test
    public void testDelete() throws Exception {

        boardDAO.delete(1000);

    }

    // 게시글 목록 테스트
    @Test
    public void testList() throws Exception {

        boardDAO.list();

    }

}
