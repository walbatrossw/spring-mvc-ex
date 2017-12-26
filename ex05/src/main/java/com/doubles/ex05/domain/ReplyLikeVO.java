package com.doubles.ex05.domain;

public class ReplyLikeVO extends BoardLikeVO {

    private Integer rno;

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    @Override
    public String toString() {
        return "ReplyLikeVO{" +
                "rno=" + rno +
                '}';
    }
}
