package com.doubles.ex04.domain;

import java.util.Date;

public class ReplyVO {

    private Integer rno;
    private Integer bno;
    private String replytext;
    private String replyer;
    private Date regdate;
    private Date updatedate;

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

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", replytext='" + replytext + '\'' +
                ", replyer='" + replyer + '\'' +
                ", regdate=" + regdate +
                ", updatedate=" + updatedate +
                '}';
    }
}
