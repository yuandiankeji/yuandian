package com.yuandian.client;

import com.yuandian.client.config.ClientConfig;
import com.yuandian.client.handler.AbstractRespHandler;
import com.yuandian.data.message.PLogin;
import com.yuandian.server.core.factory.MessageCoderFactory;
import com.yuandian.server.core.net.IoMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author twjitm 2019/4/15/22:40
 */
public class TcpClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端首次建立网络连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化网络连接");
        SessionManager.getSingleton().setClient(new NetClient(ctx.channel()));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        long uid = ClientConfig.getSingleton().getUid();
        String openId = ClientConfig.getSingleton().getOpenId();
        PLogin.Builder login = PLogin.newBuilder();
        login.setUid(uid);
        login.setOpenId(openId);
        ByteBuf b = MessageCoderFactory.getSingleton().encode((short) 3, 0, login.build().toByteArray());
        ctx.channel().writeAndFlush(b);
    }

    /**
     * 读取到服务器消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        IoMessage ioMessage = MessageCoderFactory.getSingleton().decode(ctx, byteBuf);
        AbstractRespHandler handler = MessageRegister.getHandlerMap(ioMessage.getCmd());
        handler.handler(ioMessage);
    }

}
