package com.Iqer.client.view;

import com.Iqer.client.model.IqerClientUser;
import com.Iqer.server.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Iqer(Instant Messenger for Neuqers)客户端登录界面
 */
public class IqerClientLogin extends JFrame implements ActionListener {

    //定义顶部所需要的组件
    JLabel jbl1;
    //定义中间所需要的组件
    //中部有四个JPanel，选项卡窗口管理
    JTabbedPane jtp;
    JPanel jp2, jp3, jp4, jp5;
    JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
    JButton jp2_jb1;
    JTextField jp2_jtf;
    JPasswordField jp2_jpf;
    JCheckBox jp2_jcb1, jp2_jcb2;

    //定义底部所需要的组件
    JPanel jp1;
    JButton jp1_jb1;
    JButton jp1_jb2;
    JButton jp1_jb3;

    public IqerClientLogin() {
        //处理顶部
        jbl1 = new JLabel(new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\upper.jpg"));

        //处理底部(流布局)
        jp1 = new JPanel();
        jp1_jb1 = new JButton("登录");
        //响应用户，点击登录
        jp1_jb1.addActionListener(this);
        jp1_jb2 = new JButton("清除");
        jp1_jb3 = new JButton("注册");

        //将三个按钮加入到jp1
        jp1.add(jp1_jb1);
        jp1.add(jp1_jb2);
        jp1.add(jp1_jb3);

        //处理中部
        jp2 = new JPanel(new GridLayout(3, 3));

        jp2_jbl1 = new JLabel("IQer账号", JLabel.CENTER);
        jp2_jbl2 = new JLabel("IQer密码", JLabel.CENTER);
        jp2_jbl3 = new JLabel("忘记密码", JLabel.CENTER);
        jp2_jbl3.setForeground(Color.blue);
        jp2_jbl4 = new JLabel("申请账号保护", JLabel.CENTER);
        jp2_jb1 = new JButton("清除号码");
        jp2_jtf = new JTextField();
        jp2_jpf = new JPasswordField();
        jp2_jcb1 = new JCheckBox("隐身登录");
        jp2_jcb2 = new JCheckBox("记住密码");

        //把控件按照顺序加入到jp2中
        jp2.add(jp2_jbl1);
        jp2.add(jp2_jtf);
        jp2.add(jp2_jb1);
        jp2.add(jp2_jbl2);
        jp2.add(jp2_jpf);
        jp2.add(jp2_jbl3);
        jp2.add(jp2_jcb1);
        jp2.add(jp2_jcb2);
        jp2.add(jp2_jbl4);

        //创建选项卡窗口
        jtp = new JTabbedPane();
        jtp.add("IQer号码", jp2);
        jp3 = new JPanel();
        jtp.add("手机号码", jp3);
        jp4 = new JPanel();
        jtp.add("电子邮件", jp4);
        jp5 = new JPanel();
        jtp.add("产品说明", jp5);


        //设置大小
        this.setSize(550, 340);
        //设置图标
        this.setIconImage((new ImageIcon("C:\\Users\\GXY\\IdeaProjects\\InstantMessengerClient\\src\\image\\icon.png")).getImage());
        //设置关闭窗口终止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口可见
        this.setVisible(true);
        //将jbl1加入到顶部框架（North）中
        this.add(jbl1, "North");
        //将jp1加入到底部框架（South）中
        this.add(jp1, "South");
        //将jp2、jp3、jp4共同组建的选项卡jtp加入到中部（Center）
        this.add(jtp, "Center");
    }

    public static void main(String[] args) {
        //创建登录对象
        IqerClientLogin iqerClientLogin = new IqerClientLogin();
    }

    /**
     * 用户点击登录
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果用户点击登录
        if (e.getSource() == jp1_jb1) {
            IqerClientUser iqerClientUser = new IqerClientUser();
            User u = new User();
            u.setUserID(jp2_jtf.getText().trim());
            u.setPwd(new String(jp2_jpf.getPassword()));

            if (iqerClientUser.cheackUser(u)) {
                //打开Iqer页面
                new IqerFriendList(u.getUserID());
                //同时销毁登录页面
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "请输入正确的用户名或密码");
            }

        }
    }
}
