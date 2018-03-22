package com.doubles.mvcboard.upload.persistence;

import java.util.List;

public interface ArticleFileDAO {

    // 파일 추가
    void addFile(String fullName) throws Exception;

    // 파일 목록
    List<String> getArticleFiles(Integer articleNo) throws Exception;
}
