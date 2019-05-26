package com.yuandian.test;

import com.yuandian.client.net.MessageRegister;
import com.yuandian.client.net.NetConnection;

public class TestJava {

    public static void main(String[] args) {
        MessageRegister.register("com.yuandian.test");
        new Thread(new NetConnection(8080, "127.0.0.1")).start();
    }
}
