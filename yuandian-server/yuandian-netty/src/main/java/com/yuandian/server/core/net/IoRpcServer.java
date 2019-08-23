package com.yuandian.server.core.net;

import com.yuandian.server.core.rpc.RpcService;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class IoRpcServer implements Runnable {
    private String ip;
    private int port;
    private Server server;
    private Logger logger = LoggerFactory.getLogger(IoRpcServer.class);

    public IoRpcServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        startServer();
    }

    private void startServer() {
        try {
            server = NettyServerBuilder.forAddress(new InetSocketAddress(ip, port)).addService(
                    new RpcService()).build().start();
            logger.info("[IoRpcServer] start rpc server. ip={}, port={}", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        if (server != null) {
            if (!server.isShutdown()) {
                server.shutdown();
            }
        }
    }
}
