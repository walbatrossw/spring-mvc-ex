package com.doubles.ex02.domain;

public class Criteria {

    private int page;           // 현재페이지번호
    private int perPageNum;     // 페이지 당 보여지는 게시글의 갯수

    public Criteria() {
        this.page = 1;
        this.perPageNum = 20;
    }

    public void setPage(int page) {

        // 잘못된 값이 파라미터로 넘어올 때 값을 고정
        if (page <= 0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {

        // 잘못된 값이 파라미터로 넘어올 때 값을 고정
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }

        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    // 목록 페이지마다 첫번째로 시작하는 게시물 번호를 지정하기 위한 메서드
    public int getPageStart() {
        return (this.page -1) * perPageNum;
    }

    // 목록 페이지마다 출력되는 게시물의 갯수를 지정하기 위한 메서드
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
