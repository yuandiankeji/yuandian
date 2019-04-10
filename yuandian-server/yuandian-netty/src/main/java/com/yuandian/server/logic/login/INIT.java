package com.yuandian.server.logic.login;

import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;

/**
 * 初始化账号或者登陆操作
 *
 * @author twjitm 2019/4/9/15:57
 */
public class INIT extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        long uid = 2;
        String device = "";
        UserInfo userInfo = IoClientManager.getUserInfo(uid);
        if (userInfo == null) {
            //TODO create user
        }

        IoClientManager.put(userInfo);
    }

}
