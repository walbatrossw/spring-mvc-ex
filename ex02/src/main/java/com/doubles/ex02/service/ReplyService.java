package com.doubles.ex02.service;

import com.doubles.ex02.domain.Criteria;
import com.doubles.ex02.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public void addReply(ReplyVO replyVO) throws Exception;

    public List<ReplyVO> listReply(Integer bno) throws Exception;

    public void modifyReply(ReplyVO replyVO) throws Exception;

    public void removeReply(Integer rno) throws Exception;

    public List<ReplyVO> listReplyPaging(Integer bno, Criteria criteria) throws Exception;

    public int count(Integer bno) throws Exception;

}
