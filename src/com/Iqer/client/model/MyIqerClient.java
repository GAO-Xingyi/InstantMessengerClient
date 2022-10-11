package com.Iqer.client.model;

import com.Iqer.server.common.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端：连接服务器的后台
 */
public class MyIqerClient {

    public static Socket s;

    //发送第一次请求
    public boolean sendLoginInfoServer(Object o) {
        boolean is_login = false;
        try {
            s = new Socket("127.0.0.1", 9999);
            //输入输出均从socket中得到
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message mg = (Message) ois.readObject();
            if (mg.getMType().equals("1")) {
                is_login = true;
            } else {
                //关闭socket
                s.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return is_login;
    }

    public void SendInfoToServer(Object o) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
