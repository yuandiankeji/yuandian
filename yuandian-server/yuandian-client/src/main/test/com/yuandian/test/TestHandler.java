package com.yuandian.test;

import com.yuandian.client.handler.AbstractRespHandler;
import com.yuandian.client.net.IoMessage;

public class TestHandler extends AbstractRespHandler {
    public TestHandler() {
        super(123);
    }

    public void handler(IoMessage message) {

    }
}
