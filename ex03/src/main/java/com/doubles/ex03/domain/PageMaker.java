package com.doubles.ex03.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// 페이징 처리 클래스
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

    // 게시물의 전체 갯수 setter : 페이징처리 연산 메서드 호출
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    // 페이징처리 연산 메서드
    private void calcData() {

        // 끝페이지 연산
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayNum) * displayNum);

        // 시작페이지 연산
        startPage = (endPage - displayNum) + 1;

        // 끝페이지 연산 검증
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전 활성/비활성
        prev = startPage == 1 ? false : true;

        // 다음 활성/비활성
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
    }

    // 쿼리작성을 위한 메서드
    public String makeQuery(int page) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toUriString();
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
}
