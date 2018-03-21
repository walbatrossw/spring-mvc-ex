package com.doubles.mvcboard.upload.persistence;

public interface ArticleFileDAO {

    // 파일 추가
    void addFile(String fullName) throws Exception;

    // 파일 삭제
}
