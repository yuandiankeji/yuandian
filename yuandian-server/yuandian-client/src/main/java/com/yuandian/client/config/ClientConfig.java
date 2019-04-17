package com.yuandian.client.config;

import com.yuandian.server.utils.PropertiesUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author twjitm 2019/4/16/23:32
 */
public class ClientConfig {
    private static final  ClientConfig singleton=new ClientConfig();

    public static ClientConfig getSingleton() {
        return singleton;
    }

    private long uid;
    private String name;
    private String openId;
    private long toUId;

    public void init() {
        Properties properties = PropertiesUtils.load("client.properties");
        uid = Long.parseLong(properties.getProperty("uId"));
        toUId = Long.parseLong(properties.getProperty("toUid"));
        name = properties.getProperty("name");
        openId = properties.getProperty("openid");
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getOpenId() {
        return openId;
    }

    public long getToUId() {
        return toUId;
    }
}
