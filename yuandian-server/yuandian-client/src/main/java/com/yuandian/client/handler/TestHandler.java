package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;
import com.yuandian.client.net.SessionManager;
import com.yuandian.data.common.PChatInfo;
import com.yuandian.data.message.PSendChat;

//import com.yuandian.data.message.PAuth;

public class TestHandler extends AbstractRespHandler {
    public TestHandler() {
        super(1002);
    }

    @Override
    public boolean verification(IoMessage message) {
        super.verification(message);
        return true;

    }

    @Override
    public void handler(IoMessage message) {

        PSendChat.Builder chat = PSendChat.newBuilder();
        PChatInfo.Builder chatInfo=PChatInfo.newBuilder();
        chatInfo.setUid(1);
        chatInfo.setType(1);
        chatInfo.setTargetUid(1L);
        chatInfo.setCTime(System.currentTimeMillis());
        chatInfo.setContext("hello world");
        chat.setChat(chatInfo);
//        PGetChatRecord.Builder chat=PGetChatRecord.newBuilder();
//        chat.setTargetId(1L);
//        chat.setFootMId(0);
//        chat.setLimit(System.currentTimeMillis());
//        chat.setExtra(false);
        SessionManager.getSingleton().getClient().writeData((short) 999,chat.build().toByteArray());

    }
}
