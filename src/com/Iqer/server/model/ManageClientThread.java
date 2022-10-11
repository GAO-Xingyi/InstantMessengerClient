package com.Iqer.server.model;

import java.util.HashMap;

public class ManageClientThread {

    //Hashmap中放置用户的ID号和客户端服务器连接线程
    //static设置只有一份hashmap
    public static HashMap hashMap = new HashMap<String, SerConClientThread>();

    //向hashmap中添加客户端通讯线程
    public static void addClientThread(String uid, SerConClientThread serConClientThread) {
        hashMap.put(uid, serConClientThread);
    }

    //得到UID的相应线程
    public static SerConClientThread getClientThread(String uid) {
        return (SerConClientThread) hashMap.get(uid);
    }
}
