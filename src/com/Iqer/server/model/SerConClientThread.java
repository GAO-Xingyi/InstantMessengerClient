package com.Iqer.server.model;

import com.Iqer.server.common.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class SerConClientThread extends Thread {

    private Socket s;

    public SerConClientThread(Socket s) {
        //把服务器和该客户端的连接赋给s
        this.s = s;

    }

    public void run() {

        while (true) {

            //这里可以接收客户端信息
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message) ois.readObject();

                System.out.println(m.getSender() + "给" + m.getGetter() + "说" + m.getCon());

                //服务器完成消息转发
                //这里把服务器得到的每个socket和客户端保存在Hashmap中，用客户的Id标识该Socket

                //取得接收人的通讯线程
                SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
                ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
                oos.writeObject(m);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
