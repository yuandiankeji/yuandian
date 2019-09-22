package com.yuandian.server.logic.login;

import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;

/**
 * 初始化用户
 *
 * @author twjitm 2019/9/22/23:12
 */
public class INIT extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

    }
}
