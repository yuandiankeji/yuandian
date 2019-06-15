package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;

//import com.yuandian.data.message.PAuth;

public class TestHandler extends AbstractRespHandler {
    public TestHandler() {
        super(1002);
    }

    @Override
    public void handler(IoMessage message) {
//        PAuth.Builder builder = PAuth.newBuilder();
//        builder.setUid(1L);
//        builder.setDeviceId("111");
//        builder.setToken("1212");
//        byte[] bytes = builder.build().toByteArray();


    }
}
