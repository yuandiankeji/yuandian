package com.yuandian.server.config;

import com.yuandian.server.utils.PropertiesUtils;

import java.util.Properties;

/**
 * @author twjitm 2019/3/24/22:40
 */
public class ServerConfigManager {

    private static final ServerConfigManager INSTANCE = new ServerConfigManager();

    private ServerConfig serverConfig;

    public static ServerConfigManager getInstance() {
        return INSTANCE;
    }

    public ServerConfigManager init(String config) {
        serverConfig = new ServerConfig();
        Properties properties = PropertiesUtils.load(config);
        serverConfig.load(properties);
        return INSTANCE;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

}
