package com.doubles.ex02.persistence;

import com.doubles.ex02.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    public List<ReplyVO> list(Integer bno) throws Exception;

    public void create(ReplyVO replyVO) throws Exception;

    public void update(ReplyVO replyVO) throws Exception;

    public void delete(Integer rno) throws Exception;

}
