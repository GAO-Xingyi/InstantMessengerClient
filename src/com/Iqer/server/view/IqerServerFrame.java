package com.Iqer.server.view;

import com.Iqer.server.model.IqerServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 服务器短的控制界面
 * 功能：启动服务器，关闭服务器，可以管理和监控用户
 */
public class IqerServerFrame extends JFrame implements ActionListener {
    JPanel jp1;
    JButton jb1, jb2;

    public IqerServerFrame() {
        jp1 = new JPanel();
        jb1 = new JButton("启动服务器");
        jb1.addActionListener(this);
        jb2 = new JButton("关闭服务器");
        jp1.add(jb1);
        jp1.add(jb2);


        this.add(jp1);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        IqerServerFrame iqerServerFrame = new IqerServerFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            new IqerServer();
        }
    }
}
