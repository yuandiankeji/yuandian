package com.yuandian.server.core.net;

import com.yuandian.server.config.Constant;
import com.yuandian.server.config.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by twjitm on 2019/3/25/14:04
 */
public class IoServer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(IoServer.class);

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelFuture future;
    private String ip;
    private long port;

    public IoServer(String ip, int port) {
        this.ip = ip;
        this.port = port;

    }

    public void startServer() throws Exception {

        bossGroup = new NioEventLoopGroup(Constant.NETTY_BOSS_THREAD_NUM);
        workerGroup = new NioEventLoopGroup(Constant.NETTY_WORK_THREAD_NUM);

        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 512)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(
                                    new IdleStateHandler(0, 0, 30),
                                    new LengthFieldBasedFrameDecoder(1024 * 1024, 9, 4, 0, 0, true),
                                    new TcpServerHandler());
                        }
                    }).childOption(ChannelOption.AUTO_READ, true);
            future = b.bind(this.ip, (int) this.port).sync();
            logger.debug("[IoServer] | start world success,ip={},port={}", ip, port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public void stopServer() {
        if (future != null) {
            future.channel().close();
        }

        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }

        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }

        logger.debug("[IoServer] stop world");
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
