package com.doubles.ex05.domain;

import java.util.Date;

public class BoardLikeVO {

    // 추천번호
    private int blno;
    // 게시글 번호
    private int bno;
    // 추천회원 아이디
    private String uid;
    // 추천 일시
    private Date likedate;

    public int getBlno() {
        return blno;
    }

    public void setBlno(int blno) {
        this.blno = blno;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getLikedate() {
        return likedate;
    }

    public void setLikedate(Date likedate) {
        this.likedate = likedate;
    }

    @Override
    public String toString() {
        return "BoardLikeVO{" +
                "blno=" + blno +
                ", bno=" + bno +
                ", uid='" + uid + '\'' +
                ", likedate=" + likedate +
                '}';
    }
}
