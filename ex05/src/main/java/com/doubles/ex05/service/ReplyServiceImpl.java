package com.doubles.ex05.service;

import com.doubles.ex05.domain.ReplyVO;
import com.doubles.ex05.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO replyDAO;

    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return replyDAO.list(bno);
    }

    // 댓글 입력
    @Override
    public void addReply(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
    }

    // 댓글 수정
    @Override
    public void modifyReply(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    // 댓글 삭제
    @Override
    public void removeReply(Integer rno) throws Exception {
        replyDAO.delete(rno);
    }
}
