package com.yuandian.server.logic.friends.handler;

import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;

/**
 * 移除黑名单
 */
public class RemoveBanBlack extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

    }
}
