package com.yuandian.server;

import com.yuandian.core.utils.CollectionUtil;
import com.yuandian.server.config.ServerConfig;
import com.yuandian.server.config.ServerConfigManager;
import com.yuandian.server.core.net.IoServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twjitm 2019/3/24/22:22
 */
public class YuanDianServer {
    private static Logger logger = LoggerFactory.getLogger(YuanDianServer.class);

    public static void main(String[] args) {
        logger.debug("[main] | sever start");
        String config = "server.properties";
        if (!CollectionUtil.isEmpty(args)) {
            config = args[0];
        }
        start(config);
    }

    private static void start(String args) {
        initConfig(args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("[main]| run hook");
            //TODO db
            //executor
            //server
            IoServer.getSingleton().stopServer();
        }));
    }

    private static void initConfig(String args) {
        ServerConfig serverConfig = ServerConfigManager.getInstance().init(args).getServerConfig();
        initConnection(serverConfig);
    }

    private static void initConnection(ServerConfig config) {
        try {
            IoServer.getSingleton().startServer(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
