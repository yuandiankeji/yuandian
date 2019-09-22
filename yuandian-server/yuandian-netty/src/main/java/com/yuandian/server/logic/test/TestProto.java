package com.yuandian.server.logic.test;

import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.logic.friends.service.FriendService;

import javax.swing.*;

@MessageAnnotation(cmd = MessageCmd.TEST_PROTO)
public class TestProto extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        friendService.apply(47960414809489408L, 48418949812977664L);
        friendService.apply(46750666403610624L, 48418949812977664L);
    }
}
