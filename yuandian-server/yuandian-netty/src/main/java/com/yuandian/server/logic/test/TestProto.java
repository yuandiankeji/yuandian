package com.yuandian.server.logic.test;

import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;

import javax.swing.*;

@MessageAnnotation(cmd = MessageCmd.TEST_PROTO)
public class TestProto extends AbstractTcpHandler
{
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

        SpringBeanFactory.getInstance().getUserService().getUserInfo(46750666403610624L);
        SpringBeanFactory.getInstance().getChatService().getLastChatInfo(1,1L);
    }
}
