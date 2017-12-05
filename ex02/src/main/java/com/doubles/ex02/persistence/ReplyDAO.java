package com.doubles.ex02.persistence;

import com.doubles.ex02.domain.Criteria;
import com.doubles.ex02.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    public List<ReplyVO> list(Integer bno) throws Exception;

    public void create(ReplyVO replyVO) throws Exception;

    public void update(ReplyVO replyVO) throws Exception;

    public void delete(Integer rno) throws Exception;

    public List<ReplyVO> listPaging(Integer bno, Criteria criteria) throws Exception;

    public int count(Integer bno) throws Exception;

}
