package com.yuandian.server.config;

import com.yuandian.server.utils.PropertiesUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author twjitm 2019/3/24/22:40
 */
public class ServerConfigManager {

    private static final ServerConfigManager INSTANCE = new ServerConfigManager();

    private ServerConfig serverConfig;

    private Map<String, RedisConfig> redisConfigs;

    public static ServerConfigManager getInstance() {
        return INSTANCE;
    }

    public ServerConfigManager init(String config) {
        serverConfig = new ServerConfig();
        Properties properties = PropertiesUtils.load(config);
        serverConfig.load(properties);
        initRedis();
        return INSTANCE;
    }

    public void initRedis() {
        redisConfigs = new HashMap<>();
        Properties properties = PropertiesUtils.load("redis.properties");
        RedisConfig global = new RedisConfig();
        global.setId(properties.getProperty("global.ip"));
        global.setPort(properties.getProperty("global.port"));
        global.setName("global");
        redisConfigs.put("global",global);
        RedisConfig chat = new RedisConfig();
        chat.setId(properties.getProperty("chat.ip"));
        chat.setPort(properties.getProperty("chat.port"));
        redisConfigs.put("chat", chat);
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public RedisConfig getRedisConfig(String name) {
        return redisConfigs.get(name);
    }

}
