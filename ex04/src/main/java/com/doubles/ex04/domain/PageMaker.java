package com.doubles.ex04.domain;

public class PageMaker {

    // 게시물의 전체 갯수
    private int totalCount;
    // 페이지 시작번호
    private int startPage;
    // 페이지 끝번호
    private int endPage;
    // 이전
    private boolean prev;
    // 다음
    private boolean next;
    // 페이지 번호 갯수
    private int displayNum = 10;
    // 기준클래스의 참조 변수
    private Criteria criteria;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        // endPage
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayNum) * displayNum);

        // startPage
        startPage = (endPage - displayNum) + 1;

        // endPage validation
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // perv
        prev = startPage == 1 ? false : true;

        // next
        next = endPage * criteria.getPerPageNum() >= totalCount ?  false : true;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(int displayNum) {
        this.displayNum = displayNum;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return "PageMaker{" +
                "totalCount=" + totalCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                ", displayNum=" + displayNum +
                ", criteria=" + criteria +
                '}';
    }
}
