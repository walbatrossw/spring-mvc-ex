package com.doubles.mvcboard.tutorial.domain;

import java.util.Date;

public class MessageVO {

    private Integer messageNo;
    private String receiver;
    private String sender;
    private String message;
    private Date openDate;
    private Date sendDate;

    public Integer getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(Integer messageNo) {
        this.messageNo = messageNo;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "messageNo=" + messageNo +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", openDate=" + openDate +
                ", sendDate=" + sendDate +
                '}';
    }
}
