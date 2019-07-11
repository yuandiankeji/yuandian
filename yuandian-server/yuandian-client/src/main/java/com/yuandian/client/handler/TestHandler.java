package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;
import com.yuandian.client.net.SessionManager;
import com.yuandian.data.common.PChatInfo;
import com.yuandian.data.message.PAddToBanBlack;
import com.yuandian.data.message.PGetChatUserList;
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
        chatInfo.setUid(48418949812977664L);
        chatInfo.setType(1);
        chatInfo.setTargetUid(1L);
        chatInfo.setCTime(System.currentTimeMillis());
        chatInfo.setContext("hello world");
        chat.setChat(chatInfo);
        SessionManager.getSingleton().getClient().writeData((short) 1004,chat.build().toByteArray());


    }
}
