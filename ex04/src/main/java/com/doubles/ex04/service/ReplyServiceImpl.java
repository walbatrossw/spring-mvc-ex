package com.doubles.ex04.service;

import com.doubles.ex04.domain.Criteria;
import com.doubles.ex04.domain.ReplyVO;
import com.doubles.ex04.persistence.BoardDAO;
import com.doubles.ex04.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO replyDAO;

    @Inject
    private BoardDAO boardDAO;

    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return replyDAO.list(bno);
    }

    // 댓글 목록 + 페이징
    @Override
    public List<ReplyVO> list(Integer bno, Criteria criteria) throws Exception {
        return replyDAO.list(bno, criteria);
    }

    // 댓글 갯수
    @Override
    public int listCount(Integer bno) throws Exception {
        return replyDAO.listCount(bno);
    }

    // 댓글 추가
    @Transactional
    @Override
    public void replyAdd(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
        boardDAO.updateReplyCnt(replyVO.getBno(), 1);
    }

    // 댓글 수정
    @Override
    public void replyModify(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void replyRemove(Integer rno) throws Exception {
        int bno = replyDAO.getBno(rno);
        replyDAO.delete(rno);
        boardDAO.updateReplyCnt(bno, -1);
    }

}
