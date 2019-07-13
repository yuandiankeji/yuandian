package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;

public class AcceptChatHandler extends AbstractRespHandler {

    public AcceptChatHandler() {
        super(10000);
    }

    @Override
    public void handler(IoMessage message) {
        byte[] bytes = message.getBytes();
    }
}
