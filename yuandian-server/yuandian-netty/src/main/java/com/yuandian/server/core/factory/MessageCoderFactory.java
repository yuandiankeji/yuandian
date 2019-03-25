package com.yuandian.server.core.factory;

import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.TcpMessageProcessor;
import com.yuandian.server.core.net.TcpServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author by twjitm on 2019/3/25/11:40
 */
public class MessageCoderFactory {

    private static final MessageCoderFactory SINGLETON = new MessageCoderFactory();

    public static MessageCoderFactory getSingleton() {
        return SINGLETON;
    }

    /**
     * 编码
     */
    public ByteBuf encode(short cmd, int status, byte[] data) {
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(13 + data.length);
        //协议头 2字节
        byteBuf.writeShort(2);
        //版本号 1字节
        byteBuf.writeByte(1);
        //设置消息id 2字节
        byteBuf.writeShort(cmd);
        //设置状态 4字节
        byteBuf.writeInt(status);
        //数据长度 4字节
        byteBuf.writeInt(data.length);

        //数据体
        byteBuf.writeBytes(data);
        //重新设置长度
        return byteBuf;

    }


    /**
     * 解码
     */
    public void decode(final ChannelHandlerContext ctx, final ByteBuf buff) {
        Channel channel = ctx.channel();

        int head = buff.readShort() & 0x0000FFFF;
        int version = buff.readByte() & 0x000000FF;
        int cmd = buff.readShort() & 0x0000FFFF;
        int status = buff.readInt();
        int leng = buff.readInt();
        byte[] bytes = new byte[leng];
        buff.readBytes(bytes);
        IoClient client = channel.attr(TcpServerHandler.SESSION_CLIENT).get();
        if (client == null) {
            return;
        }
        //TODO check--net package executor
        TcpMessageProcessor.getSingleton().putMessage(client, (short) cmd, bytes);

    }
}
