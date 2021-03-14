package com.yuandian.client;

import com.yuandian.client.handler.AcceptChatHandler;
import com.yuandian.client.handler.TestHandler;
import com.yuandian.client.net.MessageRegister;
import com.yuandian.client.net.NetConnection;

/**
 * @author twjitm 2019/4/15/22:45
 */
public class YuanDianClient {
    public static void main(String[] args) {
        MessageRegister.register(1002, TestHandler.class);//47.104.139.83
        MessageRegister.register(10000, AcceptChatHandler.class);
       for (int i=0;i<100;i++){
            new Thread(new NetConnection(9090, "47.104.139.83")).start();
        }

    }
}
