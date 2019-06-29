package com.yuandian.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 网络连接对象
 *
 * @author twjitm 2019/4/15/22:36
 */
public class NetConnection implements Runnable {
    private int port;
    private String ip;

    TcpClientHandler handler;

    public NetConnection(int port, String ip) {
        this.port = port;
        this.ip = ip;
        handler = new TcpClientHandler(this);
    }


    private void startup(String ip, int port) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ChannelPipeline p = ch.pipeline();
                p.addLast(new IdleStateHandler(0, 0, 30),
                        new LengthFieldBasedFrameDecoder(128 * 1024, 9, 4, 0, 0, true),
                        handler);
            }
        });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        startup(ip, port);
    }

    //掉线处理
    public void channelUnregistered() {
        startup(ip, port);
    }

    ;
}
