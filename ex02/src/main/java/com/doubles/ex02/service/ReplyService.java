package com.doubles.ex02.service;

import com.doubles.ex02.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public void addReply(ReplyVO replyVO) throws Exception;

    public List<ReplyVO> listReply(Integer bno) throws Exception;

    public void modifyReply(ReplyVO replyVO) throws Exception;

    public void removeReply(Integer rno) throws Exception;

}
