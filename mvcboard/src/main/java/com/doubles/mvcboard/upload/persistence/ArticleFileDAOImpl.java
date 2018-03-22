package com.doubles.mvcboard.upload.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleFileDAOImpl implements ArticleFileDAO {

    private static final String NAMESPACE = "com.doubles.mvcboard.mappers.upload.ArticleFileMapper";

    private final SqlSession sqlSession;

    @Inject
    public ArticleFileDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void addFile(String fileName) throws Exception {
        sqlSession.insert(NAMESPACE + ".addFile", fileName);
    }

    @Override
    public List<String> getArticleFiles(Integer articleNo) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getArticleFiles", articleNo);
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteFile", fileName);
    }

    @Override
    public void deleteFiles(Integer articleNo) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteFiles", articleNo);
    }

    @Override
    public void replaceFile(String fileName, Integer articleNo) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fileName", fileName);
        paramMap.put("articleNo", articleNo);
        sqlSession.insert(NAMESPACE + ".replaceFile", paramMap);
    }

    @Override
    public void updateFileCnt(Integer articleNo) throws Exception {
        sqlSession.update(NAMESPACE + ".updateFileCnt", articleNo);
    }
}
