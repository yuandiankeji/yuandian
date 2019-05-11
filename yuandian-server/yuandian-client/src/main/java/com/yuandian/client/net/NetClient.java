package com.yuandian.client.net;

import io.netty.channel.Channel;


public class NetClient {
    private Channel channel;

    public NetClient(Channel channel) {
        this.channel = channel;
    }

    public void writeData(short cmd, int status, byte[] data) {
        if (!isConnection()) {
            return;
        }
        MessageCoderFactory.getSingleton().encode(cmd, status, data);

    }

    public void writeData(short cmd, byte[] data) {
        writeData(cmd, 0, data);
    }

    private boolean isConnection() {
        return channel != null;
    }

    public Channel getChannel() {
        return channel;
    }

}
