package com.yuandian.client;


import io.netty.channel.Channel;

/**
 * @author twjitm 2019/4/15/22:52
 */
public class SessionManager {
    private Channel channel;
    //TODO other;
    static final SessionManager signtion = new SessionManager();

    public static SessionManager getSigntion() {
        return signtion;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
