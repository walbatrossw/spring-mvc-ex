package com.doubles.ex01.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// 페이징 처리를 위한 클래스
public class PageMaker {

    // [prev][1][2][3][4][5][6][7][8][9][10][next]

    // Example - 게시글 222개, 페이지당 10개의 게시물 출력할 경우

    // Case 1 : 현재 페이지가 3일 경우, startPage = 1, endPage = 10, prev = false, next = true
    // [1][2][(3)][4][5][6][7][8][9][10][next]

    // Case 2 : 현재 페이지가 10일 경우, startPage = 1, endPage = 10, prev = false, next = true
    // [1][2][3][4][5][6][7][8][9][(10)][next]

    // Case 3 : 현재 페이지가 11일 경우, startPage = 11, endPage = 20, prev = true, next = true
    // [prev][(11)][12][13][14][15][16][17][18][19][20][next]

    // Case 4 : 현재 페이지가 21일 경우, startPage = 21, endPage = 23, prev = true, next = false
    // [prev][(21)][22][23]

    private int totalCount; // 전체 게시글의 갯수
    private int startPage;  // 시작페이지 번호     [1]
    private int endPage;    // 끝페이지 번호       [10]
    private boolean prev;   // 이전               [prev]
    private boolean next;   // 다음               [next]

    private int displayPageNum = 10;    // 목록페이지에 보여지는 페이지의 기본갯수 [1][2][3][4][5][6][7][8][9][10]

    private Criteria criteria;          // 페이징 처리를 위한 기준 클래스

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        // 끝페이지 연산
        // 끝페이지 = Math.ceil(현재 페이지 번호 / 페이지의 갯수) * 페이지의 갯수
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

        // 시작페이지 연산
        // 시작페이지 = (끝페이지 - 페이지의 갯수) + 1
        startPage = (endPage - displayPageNum) + 1;

        // 끝페이지의 재연산 : 만약 게시글 전체 갯수가 222개라면 끝페이지는 30이아니라 23이어야한다.
        // Math.ceil(전체게시글의 갯수 / 페이지당 게시글의 갯수)
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        // 끝페이지 연산 결과가 재연산 결과보다 크면
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전버튼 : 비활성화(시작페이지가 1이면 O) / 활성화(시작페이지가 1이 아니면 X)
        prev = startPage == 1 ? false : true;

        // 다음버튼 : 비활성화((마지막페이지 * 페이지당 게시글)연산의 결과가 전체 게시글의 갯수보다 작으면) /활성화 (많으면)
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
    }

    // 쿼리 작성을 위한 메서드
    public String makeQuery(int page) {
        // URI 작성에 도움을 주는 클래스
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)   // 현재페이지
                .queryParam("perPageNum", criteria.getPerPageNum()) // 페이지당 게시글의 갯수
                .build();

        return uriComponents.toUriString();
    }

    // 검색 처리를 위한 메서드
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
            return "";
        }
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

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
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
                ", displayPageNum=" + displayPageNum +
                ", criteria=" + criteria +
                '}';
    }
}
