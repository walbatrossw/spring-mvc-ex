package com.doubles.ex04.persistence;

import com.doubles.ex04.domain.BoardVO;
import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.SearchCriteria;

import java.util.List;

public interface BoardDAO {

    // 게시글 입력 처리
    public void create(BoardVO boardVO) throws Exception;

    // 게시글 조회
    public BoardVO read(Integer bno) throws Exception;

    // 게시글 수정 처리
    public void update(BoardVO boardVO) throws Exception;

    // 게시글 삭제
    public void delete(Integer bno) throws Exception;

    // 게시글 목록
    public List<BoardVO> list() throws Exception;

    // 게시글 목록 + 페이징
    public List<BoardVO> list(Criteria criteria) throws Exception;

    // 게시글 전체 갯수
    public int listCount(Criteria criteria) throws Exception;

    // 게시글 목록 + 페이징 + 검색
    public List<BoardVO> list(SearchCriteria criteria) throws Exception;

    // 게시글 전체 갯수 or 검색된 게시글의 수
    public int listSearchCount(SearchCriteria criteria) throws Exception;

    // 특정 게시글의 댓글 갯수 갱신
    public void updateReplyCnt(Integer bno, int amount) throws Exception;

    // 게시글 조회수 갱신
    public void updateViewCnt(Integer bno) throws Exception;

    // 게시글 첨부파일 추가
    public void addAttach(String fullName) throws Exception;

}
