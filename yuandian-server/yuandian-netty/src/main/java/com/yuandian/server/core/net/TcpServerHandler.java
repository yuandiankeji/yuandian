package com.yuandian.server.core.net;

import com.yuandian.core.net.IoMessage;
import com.yuandian.core.net.MessageCoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author by twjitm on 2019/3/25/14:40
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);
    public static final AttributeKey<IoClient> SESSION_CLIENT = AttributeKey.valueOf("CLIENT");

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("[TcpServerHandler] new client into server");
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
            ctx.flush();
            return;
        }
        logger.info("[TcpServerHandler] in message accept,cmd={}", ioMessage.getCmd());
        //TODO thread do
        TcpMessageProcessor.getSingleton().putMessage(client, (short) ioMessage.getCmd(), ioMessage.getBytes());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("[TcpServerHandler]  channel in  active");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

}
