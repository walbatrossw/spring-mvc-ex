package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.Criteria;
import com.doubles.ex05.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    // 댓글 목록
    public List<ReplyVO> list(Integer bno) throws Exception;

    // 댓글 목록 + 페이징
    public List<ReplyVO> list(Integer bno, Criteria criteria) throws Exception;

    // 특정 게시글의 댓글 갯수
    public int count(Integer bno) throws Exception;

    // 댓글 입력
    public void create(ReplyVO replyVO) throws Exception;

    // 댓글 수정
    public void update(ReplyVO replyVO) throws Exception;

    // 댓글 삭제
    public void delete(Integer rno) throws Exception;

    // 특정 댓글의 게시글 번호
    public int getBno(Integer rno) throws Exception;

    // 회원이 작성한 댓글 목록
    public List<ReplyVO> userReplies(String uid) throws Exception;
}
