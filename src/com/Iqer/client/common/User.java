package com.Iqer.client.common;

/**
 * 用户类：存放用户信息
 */

//序列化可以让对象在网络上传播
public class User implements java.io.Serializable {

    private String userID;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private String pwd;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
