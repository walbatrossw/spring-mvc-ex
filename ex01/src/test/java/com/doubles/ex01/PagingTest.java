package com.doubles.ex01;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;
import com.doubles.ex01.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class PagingTest {

    @Inject
    private BoardDAO boardDAO;

    private static Logger logger = LoggerFactory.getLogger(PagingTest.class);

    // 페이징처리 테스트 1 : SQL limit 구문 테스트
    @Test
    public void testListPage() throws Exception {

        // 현재페이지 3
        int page = 3;

        // 페이지에 출력할 게시글 SELECT
        List<BoardVO> list = boardDAO.listPage(page);

        // 게시글 출력
        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

    }

    // 페이징처리 테스트 2 : Criteria 클래스를 이용한 페이징처리 테스트
    @Test
    public void testListCriteria() throws Exception {

        Criteria criteria = new Criteria();
        criteria.setPage(2);        // 현재 페이지 2
        criteria.setPerPageNum(20); // 페이지 당 게시글의 갯수 20

        // 페이지에 출력할 데이터 SELECT
        List<BoardVO> list = boardDAO.listCriteria(criteria);

        // 게시글 출력
        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

    }

    // UriComponents : URI 작성에 도움을 주는 클래스

    // URI 테스트 1
    @Test
    public void testURI() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("bno", 12) // bno=12
                .queryParam("perPageNum", 20) // perPageNum=20
                .build();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    // URI 테스트 2
    @Test
    public void testURI2() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("board", "read") // /{module}/{page} 를 각각 board/read 로 변경
                .encode();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

}
