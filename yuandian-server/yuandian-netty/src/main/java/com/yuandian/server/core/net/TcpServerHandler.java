package com.yuandian.server.core.net;

import com.yuandian.core.net.IoMessage;
import com.yuandian.core.net.MessageCoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;


/**
 * @author by twjitm on 2019/3/25/14:40
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    public static final AttributeKey<IoClient> SESSION_CLIENT = AttributeKey.valueOf("CLIENT");

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        IoClient client;
        client = new IoClient(ctx.channel());
        ctx.channel().attr(SESSION_CLIENT).set(client);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        IoMessage ioMessage = MessageCoderFactory.getSingleton().decode(ctx, byteBuf);
        IoClient client = ctx.channel().attr(TcpServerHandler.SESSION_CLIENT).get();
        if (client == null) {
            return;
        }
        //TODO thread do
        TcpMessageProcessor.getSingleton().putMessage(client, (short) ioMessage.getCmd(), ioMessage.getBytes());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

}
