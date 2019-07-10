package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;
import com.yuandian.client.net.SessionManager;
import com.yuandian.data.message.PAddToBanBlack;
import com.yuandian.data.message.PGetChatUserList;

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

        PAddToBanBlack.Builder pGetChatUserList = PAddToBanBlack.newBuilder();
        pGetChatUserList.setTargetId(1L);
        SessionManager.getSingleton().getClient().writeData((short) 1008,pGetChatUserList.build().toByteArray());


    }
}
