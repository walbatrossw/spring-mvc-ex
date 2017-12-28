package com.doubles.ex05.service;

import com.doubles.ex05.domain.BookmarkVO;

import java.util.List;

public interface BookmarkService {

    // 북마크 등록
    public void create(BookmarkVO bookmarkVO) throws Exception;

    // 북마크 목록
    public List<BookmarkVO> list(String uid) throws Exception;

    // 북마크 확인
    public boolean checkBookmark(Integer bno, String uid) throws Exception;

    // 북마크 삭제
    public void remove(Integer bno, String uid) throws Exception;

}
