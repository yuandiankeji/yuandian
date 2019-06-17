package com.yuandian.server.core.net;

import com.yuandian.core.net.MessageCoderFactory;
import com.yuandian.data.common.PErrorInfo;
import com.yuandian.data.common.PSuccessInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @author twjitm 2019/3/24/22:51
 */
public  class IoClient {

    private final int OK_STATUS = 0;

    private Channel channel;

    public IoClient(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public final ChannelFuture writeData(short cmd, byte[] data) {
        return _writeData(cmd, OK_STATUS, data);
    }

    public final ChannelFuture writeData(short cmd) {
        PSuccessInfo.Builder successInfo = PSuccessInfo.newBuilder();
        return writeData(cmd, successInfo.build().toByteArray());
    }

    public final ChannelFuture writeErrorData(short cmd, short code) {
        PErrorInfo.Builder builder = PErrorInfo.newBuilder();
        builder.setCmd(cmd);
        builder.setCode(code);
        return _writeData(cmd, code, builder.build().toByteArray());
    }

    public final ChannelFuture writeData(short cmd, int status, byte[] data) {
        return _writeData(cmd, status, data);
    }

    private ChannelFuture _writeData(short cmd, int status, byte[] data) {
        ByteBuf byteBuf = MessageCoderFactory.getSingleton().encode(cmd, status, data);
        //TODo   暂时不考虑分布式
        ChannelFuture channelFuture = null;
        if (channel != null) {
            channelFuture = channel.writeAndFlush(byteBuf);
        }
        return channelFuture;
    }

    public void close() {
        ChannelFuture future = channel.close();
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isDone()) {
                future1.channel().close();
            }
        });
    }

}
