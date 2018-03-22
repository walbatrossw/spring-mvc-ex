package com.doubles.mvcboard.article.service;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.persistence.ArticleDAO;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.commons.paging.SearchCriteria;
import com.doubles.mvcboard.upload.persistence.ArticleFileDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    private final ArticleFileDAO articleFileDAO;

    @Inject
    public ArticleServiceImpl(ArticleDAO articleDAO, ArticleFileDAO articleFileDAO) {
        this.articleDAO = articleDAO;
        this.articleFileDAO = articleFileDAO;
    }

    @Transactional
    @Override
    public void create(ArticleVO articleVO) throws Exception {

        articleDAO.create(articleVO);
        String[] files = articleVO.getFiles();

        if (files == null)
            return;
        for (String fileName : files)
            articleFileDAO.addFile(fileName);

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ArticleVO read(Integer articleNo) throws Exception {
        articleDAO.updateViewCnt(articleNo);
        return articleDAO.read(articleNo);
    }

    @Transactional
    @Override
    public void update(ArticleVO articleVO) throws Exception {
        Integer articleNo = articleVO.getArticleNo();
        String[] files = articleVO.getFiles();

        articleDAO.update(articleVO);
        articleFileDAO.deleteFiles(articleNo);

        if (files == null)
            return;
        for (String fileName : files)
            articleFileDAO.replaceFile(fileName, articleNo);
    }

    @Transactional
    @Override
    public void delete(Integer articleNo) throws Exception {
        articleFileDAO.deleteFiles(articleNo);
        articleDAO.delete(articleNo);
    }

    @Override
    public List<ArticleVO> listAll() throws Exception {
        return articleDAO.listAll();
    }

    @Override
    public List<ArticleVO> listCriteria(Criteria criteria) throws Exception {
        return articleDAO.listCriteria(criteria);
    }

    @Override
    public int countArticles(Criteria criteria) throws Exception {
        return articleDAO.countArticles(criteria);
    }

    @Override
    public List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception {
        return articleDAO.listSearch(searchCriteria);
    }

    @Override
    public int countSearchedArticles(SearchCriteria searchCriteria) throws Exception {
        return articleDAO.countSearchedArticles(searchCriteria);
    }
}
