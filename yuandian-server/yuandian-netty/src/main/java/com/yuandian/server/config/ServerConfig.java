package com.yuandian.server.config;

import java.util.Properties;

/**
 * @author twjitm 2019/3/24/22:36
 */
public class ServerConfig {
    private String ip;
    private String number;
    private long port;
    private long rpcPort;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public long getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(long rpcPort) {
        this.rpcPort = rpcPort;
    }

    public void load(Properties properties){

    }
}
