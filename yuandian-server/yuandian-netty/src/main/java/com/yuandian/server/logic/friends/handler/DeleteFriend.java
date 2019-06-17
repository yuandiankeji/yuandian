package com.yuandian.server.logic.friends.handler;

import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;

@MessageAnnotation(cmd = MessageCmd.DELETE_FRIEND)
public class DeleteFriend extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

    }
}
