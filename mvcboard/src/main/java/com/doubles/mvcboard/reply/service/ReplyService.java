package com.doubles.mvcboard.reply.service;

import com.doubles.mvcboard.reply.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    List<ReplyVO> list(Integer articleNo) throws Exception;

    void create(ReplyVO replyVO) throws Exception;

    void update(ReplyVO replyVO) throws Exception;

    void delete(Integer replyNo) throws Exception;

}
