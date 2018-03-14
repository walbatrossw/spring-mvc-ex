package com.doubles.mvcboard.reply.persistence;

import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.reply.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    List<ReplyVO> list(Integer articleNo) throws Exception;

    void create(ReplyVO replyVO) throws Exception;

    void update(ReplyVO replyVO) throws Exception;

    void delete(Integer replyNo) throws Exception;

    List<ReplyVO> listPaging(Integer articleNo, Criteria criteria) throws Exception;

    int countReplies(Integer articleNo) throws Exception;

    int getArticleNo(Integer replyNo) throws Exception;
}
