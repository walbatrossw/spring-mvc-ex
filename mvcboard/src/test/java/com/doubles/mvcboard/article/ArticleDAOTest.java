package com.doubles.mvcboard.article;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.persistence.ArticleDAO;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.commons.paging.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ArticleDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOTest.class);

    @Inject
    private ArticleDAO articleDAO;

    @Test
    public void testCreate() throws Exception {

        for (int i = 1; i <= 2000; i++) {
            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle(i+ "번째 글 제목입니다...");
            articleVO.setContent(i+ "번재 글 내용입니다...");
            articleVO.setWriter("user0"+(i%10));
            articleDAO.create(articleVO);
        }

    }

    @Test
    public void testRead() throws Exception {
        logger.info(articleDAO.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception {
        ArticleVO article = new ArticleVO();
        article.setArticleNo(1);
        article.setTitle("글 수정 테스트 제목");
        article.setContent("글 수정 테스트 내용");
        articleDAO.update(article);
    }

    @Test
    public void testDelete() throws Exception {
        articleDAO.delete(1);
    }

    @Test
    public void testListPaging() throws Exception {

        int page = 3;

        List<ArticleVO> articles = articleDAO.listPaging(page);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + ":" + article.getTitle());
        }

    }

    @Test
    public void testListCriteria() throws Exception {
        Criteria criteria = new Criteria();
        criteria.setPage(3);
        criteria.setPerPageNum(20);

        List<ArticleVO> articles = articleDAO.listCriteria(criteria);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + " : " + article.getTitle());
        }
    }

    @Test
    public void testURI() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/article/read")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/article/read?articleNo=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testURI2() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("article", "read")
                .encode();

        logger.info("/article/read?articleNo=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testDynamic1() throws Exception {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPage(1);
        searchCriteria.setKeyword("999");
        searchCriteria.setSearchType("t");

        logger.info("======================");

        List<ArticleVO> articles = articleDAO.listSearch(searchCriteria);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + " : " + article.getTitle());
        }

        logger.info("======================");

        logger.info("searched articles count : " + articleDAO.countSearchedArticles(searchCriteria));
    }

}
