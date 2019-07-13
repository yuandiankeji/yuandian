package com.yuandian.server.core.net;


/**
 * @author twjitm 2019/4/6/21:03
 */
public abstract class AbstractTcpHandler {

    public abstract void handler(IoClient client, short cmd, byte[] bytes);

    public boolean filter(short cmd) {
        return false;
    }
}
