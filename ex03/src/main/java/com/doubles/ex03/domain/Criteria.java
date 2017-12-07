package com.doubles.ex03.domain;

// 페이징 처리를 위한 기준 클래스
public class Criteria {

    // 페이지 번호
    private int page;
    // 페이지당 게시물의 갯수
    private int perPageNum;

    // 생성자 : 페이지 번호 1, 페이지당 게시물 갯수 20
    public Criteria() {
        this.page = 1;
        this.perPageNum = 20;
    }

    // 페이지 번호 setter : invalid value check
    public void setPage(int page) {

        // 페이지 번호가 0이하의 작은 값일 경우, 1로 고정
        if (page <= 0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    // 페이지당 게시물의 갯수 setter : invalid value check
    public void setPerPageNum(int perPageNum) {

        // 페이지당 개시물의 갯수가 0이하 이거나, 100 초과일 경우, 20으로 고정
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 20;
            return;
        }

        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    // For MyBatis SQLMapper
    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    // For MyBatis SQLMapper
    public int getPerPageNum() {
        return this.perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}
