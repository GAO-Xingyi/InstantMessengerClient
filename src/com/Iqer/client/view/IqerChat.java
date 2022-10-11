package com.Iqer.client.view;
/**
 * 因为客户端要处于读取的状态，因此我们把他做成一个线程
 */

import com.Iqer.server.common.Message;
import com.Iqer.client.model.MyIqerClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 聊天界面
 */
public class IqerChat extends JFrame implements ActionListener, Runnable {

    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    String ownerID;
    String personID;//聊天对象ID

    public IqerChat(String ownerId, String person) {
        //将自己的ID和聊天对象的ID传值到该类变量中
        this.ownerID = ownerId;
        this.personID = person;

        jta = new JTextArea();
        jtf = new JTextField(25);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);


        this.add(jta, "Center");
        this.add(jp, "South");
        this.setTitle(ownerId + "正在和" + person + "对话");
        this.setIconImage((new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\icon.png")).getImage());
        this.setSize(750, 450);
        this.setVisible(true);
    }

    /**
     * 发送按钮监听
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果用户点击了发送按钮
        if (e.getSource() == jb) {

            Message msg = new Message();
            msg.setSender(this.ownerID);
            msg.setGetter(this.personID);
            msg.setCon(jtf.getText());
            msg.setSendTime(new Date().toString());
            this.jta.append(msg.getCon());
            //发送给服务器
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(MyIqerClient.s.getOutputStream());
                objectOutputStream.writeObject(msg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                //读取msg(读取这里有阻塞，如果读取不了就等待在这)
                ObjectInputStream ois = new ObjectInputStream(MyIqerClient.s.getInputStream());

                Message msg = (Message) ois.readObject();

                //显示msg
                String info = msg.getSender() + "对" + msg.getGetter() + "说:" + msg.getCon() + "....." + msg.getSendTime() + "\r\n";
                this.jta.append(info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
