package com.Iqer.server.model;

import com.Iqer.server.common.Message;
import com.Iqer.server.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Iqer服务器
 * 功能：监听，等待客服端连接
 */
public class IqerServer {

    public IqerServer() {

        try {
            //在123456端口监听
            System.out.println("服务器在9999端口监听");
            ServerSocket ss = new ServerSocket(9999);
            //阻塞，等待连接

            /**
             * 设置监听线程
             * 试想一下：如果第一次输入密码错误，没有线程保持监听
             * 而迫使监听结束，则第二次输入密码时将不再响应
             */
            while (true) {
                Socket socket = ss.accept();
                /**
                 * 接受客户端发来的信息
                 */
                //字符串流
//            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //对象流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User u = (User) ois.readObject();
                Message m = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                if (u.getPwd().equals("123456")) {
                    m.setMType("1");
                    oos.writeObject(m);

                    //这里单开一个线程
                    //让该线程与该客户端保持通讯
                    SerConClientThread thread = new SerConClientThread(socket);
                    ManageClientThread.addClientThread(u.getUserID(), thread);
                    //启动与该客户端通信的线程
                    thread.start();
                } else {
                    m.setMType("2");
                    oos.writeObject(m);
                    //密码错误，关闭连接
                    socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
