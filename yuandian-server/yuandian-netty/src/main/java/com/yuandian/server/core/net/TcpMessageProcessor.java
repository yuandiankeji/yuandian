package com.yuandian.server.core.net;

import com.yuandian.core.utils.ClassLoadUtil;
import com.yuandian.server.core.annotation.MessageAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private Map<Short, Class> handlerMap = new ConcurrentHashMap<>();

    public void init() {
        //@TODO  Thread manager
        executorService = Executors.newSingleThreadExecutor();

        List<Class> classList = ClassLoadUtil.getSubClasses(AbstractTcpHandler.class, "com.yuandian.server.logic");
        for (Class clazz : classList) {
            MessageAnnotation messageAnnotation = (MessageAnnotation
                    ) clazz.getAnnotation(MessageAnnotation.class);
            if (messageAnnotation != null) {
                handlerMap.put(messageAnnotation.cmd(), clazz);
            }
        }
    }

    public void putMessage(IoClient client, short cmd, byte[] data) {
        executorService.execute(new ProcessLogic(client, cmd, data));

    }

    class ProcessLogic implements Runnable {

        IoClient client;
        short cmd;
        byte[] data;

        public ProcessLogic(IoClient client, short cmd, byte[] data) {
            this.client = client;
            this.cmd = cmd;
            this.data = data;
        }

        @Override
        public void run() {
            Class clazz = handlerMap.get(cmd);
            try {
                AbstractTcpHandler handler = (AbstractTcpHandler) clazz.newInstance();

                logger.info("[ProcessLogic] | handler message cmd={},data={}", cmd,data);
                handler.handler(client, cmd, data);
            } catch (InstantiationException ignored) {
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
