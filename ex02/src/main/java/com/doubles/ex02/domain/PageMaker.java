package com.doubles.ex02.domain;

public class PageMaker {

    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayNum = 10;
    private Criteria criteria;

    private void calcData() {

        // 끝페이지 연산
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayNum) * displayNum);

        // 시작페이지 연산
        startPage = (endPage - displayNum) + 1;

        // 끝페이지 재연산
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if (endPage > tempEndPage) {
            endPage =  tempEndPage;
        }

        // 이전버튼
        prev = startPage == 1 ? false : true;

        // 다음버튼
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
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
