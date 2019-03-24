package com.yuandian.server;

import com.yuandian.server.config.ServerConfig;
import com.yuandian.server.config.ServerConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twjitm 2019/3/24/22:22
 */
public class YuanDianServer {
    private static Logger logger = LoggerFactory.getLogger(YuanDianServer.class);

    public static void main(String[] args) {
        logger.debug("[main] | sever start");
        start(args[0]);
    }

    private static void start(String args) {
        initConfig(args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("[main]| run hook");
        }));
    }

    private static void initConfig(String args) {
        ServerConfig serverConfig = ServerConfigManager.getInstance().init(args).getServerConfig();
        initConnection(serverConfig);
    }

    private static void initConnection(ServerConfig config) {

    }
}
