package com.yuandian.server.core.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

/**
 * @author by twjitm on 2019/3/25/20:02
 */
public class TcpMessageProcessor {
    private static TcpMessageProcessor singleton = new TcpMessageProcessor();

    public static TcpMessageProcessor getSingleton() {
        return singleton;
    }
    private ExecutorService executorService;

    public Logger logger = LoggerFactory.getLogger(TcpMessageProcessor.class);

    public void putMessage(IoClient client,short cmd, byte[] data) {

    }
}
