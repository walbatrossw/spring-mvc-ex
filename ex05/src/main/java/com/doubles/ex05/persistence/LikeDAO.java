package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.ReplyLikeVO;

public interface LikeDAO {

    // 게시글 추천 하기
    public void createBoardLike(BoardLikeVO boardLikeVO) throws Exception;

    // 게시글 추천 취소
    public void deleteBoardLike(BoardLikeVO boardLikeVO) throws Exception;

    // 게시글 추천 갯수
    public int countBoardLikes(Integer bno) throws Exception;

    // 게시글 추천 여부
    public boolean checkBoardLike(Integer bno, String uid) throws Exception;

    // 댓글 추천 하기
    public void createReplyLike(ReplyLikeVO replyLikeVO) throws Exception;

    // 댓글 추천 취소
    public void deleteReplyLike(ReplyLikeVO replyLikeVO) throws Exception;

    // 댓글 추천 갯수
    public int countReplyLikes(Integer rno) throws Exception;

    // 댓글 추천 여부
    public boolean checkReplyLike(Integer rno, String uid) throws Exception;

}
