package com.doubles.mvcboard.reply.service;

import com.doubles.mvcboard.article.persistence.ArticleDAO;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.reply.domain.ReplyVO;
import com.doubles.mvcboard.reply.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDAO;

    private final ArticleDAO articleDAO;

    @Inject
    public ReplyServiceImpl(ReplyDAO replyDAO, ArticleDAO articleDAO) {
        this.replyDAO = replyDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    public List<ReplyVO> getReplies(Integer articleNo) throws Exception {
        return replyDAO.list(articleNo);
    }

    @Transactional
    @Override
    public void addReply(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
        articleDAO.updateReplyCnt(replyVO.getArticleNo(), 1);
    }

    @Override
    public void modifyReply(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    @Transactional
    @Override
    public void removeReply(Integer replyNo) throws Exception {
        int articleNo = replyDAO.getArticleNo(replyNo);
        replyDAO.delete(replyNo);
        articleDAO.updateReplyCnt(articleNo, -1);
    }

    @Override
    public List<ReplyVO> getRepliesPaging(Integer articleNo, Criteria criteria) throws Exception {
        return replyDAO.listPaging(articleNo, criteria);
    }

    @Override
    public int countReplies(Integer articleNo) throws Exception {
        return replyDAO.countReplies(articleNo);
    }

}
