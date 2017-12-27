package com.doubles.ex05.domain;

import java.util.Date;

public class ReplyVO {

    // 댓글 번호
    private Integer rno;
    // 게시글 번호
    private Integer bno;
    // 댓글 내용
    private String replytext;
    // 댓글 작성자
    private String replyer;
    // 댓글 작성자 정보
    private UserVO userVO;
    // 댓글 등록일자
    private Date regdate;
    // 댓글 수정일자
    private Date updatedate;
    // 댓글 추천수
    private Integer rlnocount;

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getReplytext() {
        return replytext;
    }

    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getRlnocount() {
        return rlnocount;
    }

    public void setRlnocount(Integer rlnocount) {
        this.rlnocount = rlnocount;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", replytext='" + replytext + '\'' +
                ", replyer='" + replyer + '\'' +
                ", userVO=" + userVO +
                ", regdate=" + regdate +
                ", updatedate=" + updatedate +
                ", rlnocount=" + rlnocount +
                '}';
    }
}
