package com.yuandian.server.core.net;

import com.yuandian.server.core.net.IoClient;

/**
 * @author twjitm 2019/4/6/21:03
 */
public abstract class AbstractTcpHandler {

    public abstract void handler(IoClient client, short cmd,byte[] bytes);
}
