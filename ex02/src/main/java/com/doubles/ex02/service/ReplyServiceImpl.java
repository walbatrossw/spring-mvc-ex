package com.doubles.ex02.service;

import com.doubles.ex02.domain.ReplyVO;
import com.doubles.ex02.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO replyDAO;

    @Override
    public void addReply(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
    }

    @Override
    public List<ReplyVO> listReply(Integer bno) throws Exception {
        return replyDAO.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    @Override
    public void removeReply(Integer rno) throws Exception {
        replyDAO.delete(rno);
    }
}
