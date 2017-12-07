package com.doubles.ex03.service;

import com.doubles.ex03.domain.Criteria;
import com.doubles.ex03.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    // 특정 게시글의 댓글 목록
    public List<ReplyVO> listReply(Integer bno) throws Exception;

    // 댓글 입력
    public void addReply(ReplyVO replyVO) throws Exception;

    // 댓글 수정
    public void modifyReply(ReplyVO replyVO) throws Exception;

    // 댓글 삭제
    public void removeReply(Integer rno) throws Exception;

    // 특정 게시글의 댓글 목록 + 페이징
    public List<ReplyVO> listReply(Integer bno, Criteria criteria) throws Exception;

    // 특정 게시글의 댓글 갯수
    public int replyCount(Integer bno) throws Exception;
}
