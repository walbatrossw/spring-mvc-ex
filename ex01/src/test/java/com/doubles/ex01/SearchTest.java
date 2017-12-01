package com.doubles.ex01;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.SearchCriteria;
import com.doubles.ex01.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class SearchTest {

    @Inject
    private BoardDAO boardDAO;

    private static Logger logger = LoggerFactory.getLogger(SearchTest.class);

    @Test
    public void testDynamic1() throws Exception {

        SearchCriteria criteria = new SearchCriteria();
        criteria.setPage(1);
        criteria.setKeyword("글");
        criteria.setSearchType("t");

        logger.info("============================================");

        List<BoardVO> list = boardDAO.listSearch(criteria);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

        logger.info("============================================");

        logger.info("COUNT : " + boardDAO.listSearchCount(criteria));
    }

}
