package com.doubles.ex05.domain;

import java.util.Date;

public class BookmarkVO {

    private Integer bmno;
    private Integer bno;
    private String uid;
    private Date regdate;
    private BoardVO boardVO;

    public Integer getBmno() {
        return bmno;
    }

    public void setBmno(Integer bmno) {
        this.bmno = bmno;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public BoardVO getBoardVO() {
        return boardVO;
    }

    public void setBoardVO(BoardVO boardVO) {
        this.boardVO = boardVO;
    }

    @Override
    public String toString() {
        return "BookmarkVO{" +
                "bmno=" + bmno +
                ", bno=" + bno +
                ", uid='" + uid + '\'' +
                ", regdate=" + regdate +
                ", boardVO=" + boardVO +
                '}';
    }
}
