package com.doubles.mvcboard.upload.service;

import com.doubles.mvcboard.upload.persistence.ArticleFileDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ArticleFileServiceImpl implements ArticleFileService {

    private final ArticleFileDAO articleFileDAO;

    @Inject
    public ArticleFileServiceImpl(ArticleFileDAO articleFileDAO) {
        this.articleFileDAO = articleFileDAO;
    }

    @Override
    public List<String> getArticleFiles(Integer articleNo) throws Exception {
        return articleFileDAO.getArticleFiles(articleNo);
    }

    @Transactional
    @Override
    public void deleteFile(String fileName, Integer articleNo) throws Exception {
        articleFileDAO.deleteFile(fileName);
        articleFileDAO.updateFileCnt(articleNo);
    }
}
