package com.doubles.ex03.persistence;

import com.doubles.ex03.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    // 댓글 목록
    public List<ReplyVO> list(Integer bno) throws Exception;

    // 댓글 입력
    public void create(ReplyVO replyVO) throws Exception;

    // 댓글 수정
    public void update(ReplyVO replyVO) throws Exception;

    // 댓글 삭제
    public void delete(Integer rno) throws Exception;

}
