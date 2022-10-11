package com.Iqer.client.model;

import com.Iqer.server.common.User;

/**
 * 验证Iqer是否登录
 * 验证时，需要数据库，由于数据库不在本地（在服务器），所以需要写程序委托服务器
 */
public class IqerClientUser {
    public boolean cheackUser(User u) {

        return new MyIqerClient().sendLoginInfoServer(u);

    }
}
