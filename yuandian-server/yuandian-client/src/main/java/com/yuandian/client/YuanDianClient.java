package com.yuandian.client;

/**
 * @author twjitm 2019/4/15/22:45
 */
public class YuandianClient {
    public static void main(String[] args) {
        MessageRegister.register();
        new Thread(new NetConnection(8080, "127.0.0.1")).start();
    }
}
