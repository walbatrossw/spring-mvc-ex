package com.doubles.ex04.domain;

public class Criteria {

    // 페이지 번호
    private int page;
    // 페이지당 게시물의 갯수
    private int perPageNum;

    // default constructor
    public Criteria() {
        this.page = 1;
        this.perPageNum = 20;
    }

    // page validation check
    public void setPage(int page) {

        if (page <= 0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    // perPageNum validation check
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
