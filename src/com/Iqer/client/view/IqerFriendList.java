package com.Iqer.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 好友列表（包括：同学名单、导师名单、黑名单）
 */
public class IqerFriendList extends JFrame implements ActionListener, MouseListener {

    //把整个JFrame设置成CardLayout布局
    CardLayout cl;

    //处理第一张卡片
    JPanel jpfriend1, jpfriend2, jpfriend3;
    //卡片布局
    //三个按钮
    JButton jpfriend_jb1, jpfriend_jb2, jpfriend_jb3;
    //滚动条
    JScrollPane jsp1;

    //处理第二张卡片
    JPanel jptutor1, jptutor2, jptutor3;
    //卡片布局
    //三个按钮
    JButton jptutor_jb1, jptutor_jb2, jptutor_jb3;
    //滚动条
    JScrollPane jsp2;

    String owner;


    public IqerFriendList(String ownerId) {

        this.owner = ownerId;

        /**
         *  处理第一张卡片(显示好友列表)
         */

        //三张卡片
        jpfriend_jb1 = new JButton("我的同学");
        jpfriend_jb2 = new JButton("我的导师");
        jpfriend_jb2.addActionListener(this);
        jpfriend_jb3 = new JButton("黑名单");

        jpfriend1 = new JPanel(new BorderLayout());
        //有50个好友
        jpfriend2 = new JPanel(new GridLayout(50, 1, 4, 4));
        //初始化50个好友
        JLabel[] jbls = new JLabel[50];
        for (int i = 0; i < jbls.length; i++) {
            jbls[i] = new JLabel("同学" + (i + 1) + " ", new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\profile.png"), JLabel.LEFT);
            jbls[i].addMouseListener(this);
            jpfriend2.add(jbls[i]);
        }
        //陌导师和黑名单
        jpfriend3 = new JPanel(new GridLayout(2, 1));
        //将“我的导师”和“黑名单”加入到jpfriend3中
        jpfriend3.add(jpfriend_jb2);
        jpfriend3.add(jpfriend_jb3);


        jsp1 = new JScrollPane(jpfriend2);

        //北边jpfriend1进行初始化
        jpfriend1.add(jpfriend_jb1, "North");
        jpfriend1.add(jsp1, "Center");
        jpfriend1.add(jpfriend3, "South");

        /**
         * 处理第二张卡片（导师）
         */

        //三张卡片
        jptutor_jb1 = new JButton("我的同学");
        jptutor_jb1.addActionListener(this);
        jptutor_jb2 = new JButton("我的导师");
        jptutor_jb3 = new JButton("黑名单");

        jptutor1 = new JPanel(new BorderLayout());
        //有25个导师
        jptutor2 = new JPanel(new GridLayout(25, 1, 4, 4));
        //初始化25个导师
        JLabel[] jbls2 = new JLabel[25];
        for (int i = 0; i < jbls2.length; i++) {
            jbls2[i] = new JLabel("导师" + (i + 1) + " ", new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\profile.png"), JLabel.LEFT);
            jbls2[i].addMouseListener(this);
            jptutor2.add(jbls2[i]);
        }
        //好友和黑名单
        jptutor3 = new JPanel(new GridLayout(2, 1));
        //将“我的导师”和“黑名单”加入到jpfriend3中
        jptutor3.add(jptutor_jb1);
        jptutor3.add(jptutor_jb2);


        jsp2 = new JScrollPane(jptutor2);

        //北边jpfriend1进行初始化
        jptutor1.add(jptutor3, "North");
        jptutor1.add(jsp2, "Center");
        jptutor1.add(jptutor_jb3, "South");


        cl = new CardLayout();
        this.setLayout(cl);
        this.add(jpfriend1, "1");
        this.add(jptutor1, "2");

        //再窗口显示自己的编号
        this.setTitle(ownerId);
        this.setSize(250, 550);
        this.setIconImage((new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\icon.png")).getImage());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //如果点击了导师按钮,显示第二张卡片
        if (e.getSource() == jpfriend_jb2) {
            cl.show(this.getContentPane(), "2");
        } else if (e.getSource() == jptutor_jb1) {
            cl.show(this.getContentPane(), "1");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //响应用户双击的事件，并得到好友的编号
        if (e.getClickCount() == 2) {
            //得到好友的编号
            String friendNo1 = ((JLabel) e.getSource()).getText();
            String friendNo = friendNo1.replaceAll(" ", "");
            System.out.println("你要和" + friendNo + "聊天");
            IqerChat iqerChat = new IqerChat(this.owner, friendNo);
            //启动聊天线程
            Thread thread = new Thread(iqerChat);
            thread.start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * 设置鼠标触碰头像高亮，离开恢复正常
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jl = (JLabel) e.getSource();
        jl.setForeground(Color.RED);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jl = (JLabel) e.getSource();
        jl.setForeground(Color.BLACK);
    }


}



