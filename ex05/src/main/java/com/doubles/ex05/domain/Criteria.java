package com.doubles.ex05.domain;

public class Criteria {

    // 페이지 번호
    private int page;
    // 페이지 당 게시물 갯수
    private int perPageNum;

    // 기본생성자 : 1페이지, 페이지 당 게시물 갯수 20으로 초기화
    public Criteria() {
        this.page = 1;
        this.perPageNum = 20;
    }

    // 페이지 번호 유효성 체크
    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    // 페이지 당 게시물 갯수 유효성 체크
    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 20;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    // for MyBatis SQL Mapper
    public int getPerPageNum() {
        return this.perPageNum;
    }

    // for MyBatis SQL Mapper
    // 페이지 블럭 시작번호
    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}
