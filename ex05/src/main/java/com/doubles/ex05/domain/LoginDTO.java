package com.doubles.ex05.domain;

// DTO : 화면에서 전달되는 데이터를 수정하는 용도 : 아이디, 비밀번호, 로그인 유지 여부
public class LoginDTO {

    private String uid; // 아이디
    private String upw; // 비밀번호
    private boolean useCookie; // 로그인 유지 여부

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpw() {
        return upw;
    }

    public void setUpw(String upw) {
        this.upw = upw;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "uid='" + uid + '\'' +
                ", upw='" + upw + '\'' +
                ", useCookie=" + useCookie +
                '}';
    }
}
