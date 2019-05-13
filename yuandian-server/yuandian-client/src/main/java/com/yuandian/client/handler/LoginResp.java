package com.yuandian.client.handler;

import com.yuandian.client.net.IoMessage;


/**
 * @author twjitm 2019/4/15/23:15
 */
public class LoginResp extends AbstractRespHandler {

    public LoginResp() {
        super(3);
    }

    @Override
    public void handler(IoMessage message) {
        byte[] bytes = message.getBytes();
        //Todo 你的业务逻辑

    }
}
