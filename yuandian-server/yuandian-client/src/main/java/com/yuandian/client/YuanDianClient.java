package com.yuandian.client;

import com.yuandian.client.config.ClientConfig;

/**
 * @author twjitm 2019/4/15/22:45
 */
public class YuanDianClient {
    public static void main(String[] args) {
        MessageRegister.register();
        ClientConfig.getSingleton().init();
        new Thread(new NetConnection(8080, "127.0.0.1")).start();
    }
}
