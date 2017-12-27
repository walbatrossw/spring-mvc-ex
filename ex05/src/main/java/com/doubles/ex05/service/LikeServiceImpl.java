package com.doubles.ex05.service;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.ReplyLikeVO;
import com.doubles.ex05.persistence.LikeDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class LikeServiceImpl implements LikeService {

    @Inject
    private LikeDAO likeDAO;

    @Override
    public void createBoardLike(BoardLikeVO boardLikeVO) throws Exception {
        likeDAO.createBoardLike(boardLikeVO);
    }

    @Override
    public void removeBoardLike(BoardLikeVO boardLikeVO) throws Exception {
        likeDAO.deleteBoardLike(boardLikeVO);
    }

    @Override
    public int countBoardLikes(Integer bno) throws Exception {
        return likeDAO.countBoardLikes(bno);
    }

    @Override
    public boolean checkBoardLike(Integer bno, String uid) throws Exception {
        return likeDAO.checkBoardLike(bno, uid);
    }

    @Override
    public void createReplyLike(ReplyLikeVO replyLikeVO) throws Exception {
        likeDAO.createReplyLike(replyLikeVO);
    }

    @Override
    public void removeReplyLike(ReplyLikeVO replyLikeVO) throws Exception {
        likeDAO.deleteReplyLike(replyLikeVO);
    }

    @Override
    public int countReplyLikes(Integer rno) throws Exception {
        return likeDAO.countReplyLikes(rno);
    }

    @Override
    public boolean checkReplyLike(Integer rno, String uid) throws Exception {
        return likeDAO.checkReplyLike(rno, uid);
    }
}
