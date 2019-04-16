package com.yuandian.client.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.push.PushChatMessage;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoMessage;
import com.yuandian.server.logic.AbstractTcpHandler;

/**
 * @author twjitm 2019/4/17/0:03
 */

public class ReceiveChatResp extends AbstractRespHandler {


    public ReceiveChatResp() {
        super(1000);
    }

    @Override
    public void handler(IoMessage message) {
        try {
            PushChatMessage chat = PushChatMessage.parseFrom(message.getBytes());
            System.out.println("收到消息：" + chat.getContext());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
