package com.doubles.ex04.service;

import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    // 댓글 목록
    public List<ReplyVO> list(Integer bno) throws Exception;

    // 댓글 목록 + 페이징
    public List<ReplyVO> list(Integer bno, Criteria criteria) throws Exception;

    // 댓글 갯수
    public int listCount(Integer bno) throws Exception;

    // 댓글 추가
    public void replyAdd(ReplyVO replyVO) throws Exception;

    // 댓글 수정
    public void replyModify(ReplyVO replyVO) throws Exception;

    // 댓글 삭제
    public void replyRemove(Integer rno) throws Exception;


}
