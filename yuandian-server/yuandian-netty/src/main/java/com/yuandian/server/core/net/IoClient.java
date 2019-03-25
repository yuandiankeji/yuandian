package com.yuandian.server.core.net;

import com.yuandian.server.core.factory.MessageCoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * @author twjitm 2019/3/24/22:51
 */
public abstract class IoClient {

    private final int OK_STATUS = 0;

    private Channel channel;

    public IoClient(Channel channel) {
        this.channel = channel;
    }

    public final ChannelFuture writeData(short cmd, byte[] data) {
        return _writeData(cmd, OK_STATUS, data);
    }

    private ChannelFuture _writeData(short cmd, int status, byte[] data) {
        ByteBuf byteBuf = MessageCoderFactory.getSingleton().encode(cmd, status, data);
        ChannelFuture channelFuture = null;
        if (channel != null) {
            channelFuture = channel.writeAndFlush(byteBuf);
        }
        return channelFuture;
    }

}
