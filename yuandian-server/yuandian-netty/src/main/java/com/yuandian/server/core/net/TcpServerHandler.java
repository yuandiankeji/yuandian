package com.yuandian.server.core.net;

import com.yuandian.server.core.factory.MessageCoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;


/**
 * @author by twjitm on 2019/3/25/14:40
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    public static final AttributeKey<IoClient> SESSION_CLIENT = AttributeKey.valueOf("CLIENT");
    public static final AttributeKey<Long> SESSION_CLIENT_ID = AttributeKey.valueOf("SESSION_CLIENT_ID");

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        IoClient client;
        client = new IoClient(ctx.channel());
        ctx.channel().attr(SESSION_CLIENT).set(client);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        MessageCoderFactory.getSingleton().decode(ctx, byteBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

}
