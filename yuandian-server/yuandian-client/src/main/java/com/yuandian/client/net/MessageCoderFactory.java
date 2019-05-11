package com.yuandian.client.net;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
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
    public IoMessage decode(final ChannelHandlerContext ctx, final ByteBuf buff) {
       IoMessage ioMessage = new IoMessage();
        int head = buff.readShort() & 0x0000FFFF;
        int version = buff.readByte() & 0x000000FF;
        int cmd = buff.readShort() & 0x0000FFFF;
        int status = buff.readInt();
        int length = buff.readInt();
        byte[] bytes = new byte[length];
        buff.readBytes(bytes);
        ioMessage.setHead(head);
        ioMessage.setCmd(cmd);
        ioMessage.setStatus(status);
        ioMessage.setVersion(version);
        ioMessage.setBytes(bytes);
        ioMessage.setLength(length);
        return ioMessage;
    }
}
