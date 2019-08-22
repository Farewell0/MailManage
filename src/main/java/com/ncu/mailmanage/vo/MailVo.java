package com.ncu.mailmanage.vo;

import java.util.Date;

/**
 * MailVo
 *
 * @author wzzfarewell
 * @date 2019/8/20
 **/
public class MailVo {

    private Long mailId;

    private String title;

    private Date sendTime;

    private String body;

    private String sender;

    private String receiver;

    private Long attrId;


    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    @Override
    public String toString() {
        return "MailVo{" +
                "mailId=" + mailId +
                ", title='" + title + '\'' +
                ", sendTime=" + sendTime +
                ", body='" + body + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
