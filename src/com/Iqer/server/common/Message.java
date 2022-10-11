package com.Iqer.server.common;

/**
 * 服务器与客户端的信息交互
 * <p>
 * 三类Message
 * MType1:登录失败
 * MType2:登陆成功
 * MTyoe3:普通消息
 */
public class Message implements java.io.Serializable {

    private String MType;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    private String sender;
    private String getter;
    private String con;
    private String sendTime;

    public String getMType() {
        return MType;
    }

    public void setMType(String MType) {
        this.MType = MType;
    }

}
