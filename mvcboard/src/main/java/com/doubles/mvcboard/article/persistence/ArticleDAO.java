package com.doubles.mvcboard.article.persistence;

import com.doubles.mvcboard.article.domain.ArticleVO;

import java.util.List;

public interface ArticleDAO {

    public void create(ArticleVO articleVO) throws Exception;

    public ArticleVO read(Integer articleNo) throws Exception;

    public void update(ArticleVO articleVO) throws Exception;

    public void delete(Integer articleNo) throws Exception;

    public List<ArticleVO> listAll() throws Exception;

}
