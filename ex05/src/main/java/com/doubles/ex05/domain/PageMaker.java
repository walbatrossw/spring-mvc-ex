package com.doubles.ex05.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {

    // 게시물 전체 갯수
    private int totalCount;

    // 페이지 시작번호
    private int startPage;

    // 페이지 끝번호
    private int endPage;

    // 이전
    private boolean prev;

    // 다음
    private boolean next;

    // 페이지 블럭 번호 갯수
    private int displayNum = 10;

    // 기준 클래스 참조변수
    private Criteria criteria;

    public int getTotalCount() {
        return totalCount;
    }

    // 게시물 전체 갯수가 입력되면 페이징 처리 연산 처리
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        // 페이지 끝번호 연산
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayNum) * displayNum);

        // 페이지 시작번호 연산
        startPage = (endPage - displayNum) + 1;

        // 끝페이지 검증
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전 활성/비활성
        prev = startPage == 1 ? false : true;

        // 다음 활성/비활성
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
    }

    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();
        return uriComponents.toUriString();
    }

    public String makeSearch(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
                .queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword()))
                .build();
        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        if (keyword == null || keyword.trim().length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
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
