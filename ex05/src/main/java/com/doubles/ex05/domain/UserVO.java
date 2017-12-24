package com.doubles.ex05.domain;

import java.util.Date;

public class UserVO {

    private String uid;
    private String upw;
    private String uname;
    private String uemail;
    private int upoint;
    private Date regdate;
    private Date logdate;
    private String uimage;

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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public int getUpoint() {
        return upoint;
    }

    public void setUpoint(int upoint) {
        this.upoint = upoint;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid='" + uid + '\'' +
                ", upw='" + upw + '\'' +
                ", uname='" + uname + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upoint=" + upoint +
                ", regdate=" + regdate +
                ", logdate=" + logdate +
                ", uimage='" + uimage + '\'' +
                '}';
    }
}
