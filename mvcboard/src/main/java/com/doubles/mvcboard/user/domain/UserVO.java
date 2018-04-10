package com.doubles.mvcboard.user.domain;

import java.util.Date;

public class UserVO {

    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private Date userJoinDate;
    private Date userLoginDate;
    private String userSignature;
    private String userImg;
    private int userPoint;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserJoinDate() {
        return userJoinDate;
    }

    public void setUserJoinDate(Date userJoinDate) {
        this.userJoinDate = userJoinDate;
    }

    public Date getUserLoginDate() {
        return userLoginDate;
    }

    public void setUserLoginDate(Date userLoginDate) {
        this.userLoginDate = userLoginDate;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userJoinDate=" + userJoinDate +
                ", userLoginDate=" + userLoginDate +
                ", userSignature='" + userSignature + '\'' +
                ", userImg='" + userImg + '\'' +
                ", userPoint=" + userPoint +
                '}';
    }
}
