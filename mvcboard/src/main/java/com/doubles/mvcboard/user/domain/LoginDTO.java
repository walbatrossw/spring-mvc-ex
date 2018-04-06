package com.doubles.mvcboard.user.domain;

public class LoginDTO {

    private String userId;
    private String userPw;
    private boolean useCookie;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
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
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", useCookie=" + useCookie +
                '}';
    }
}
