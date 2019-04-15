package com.yuandian.client;

import com.yuandian.client.handler.AbstractRespHandler;
import com.yuandian.core.utils.ClassLoadUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author twjitm 2019/4/15/23:24
 */
public class MessageRegister {
    private static ConcurrentHashMap<Integer, AbstractRespHandler> handlerMap = new ConcurrentHashMap<>();

    public static void register() {
        List<Class> classList = ClassLoadUtil.getSubClasses(AbstractRespHandler.class, "com.yuandian.client.handler");
        for (Class clazz : classList) {
            try {
                AbstractRespHandler handler = (AbstractRespHandler) clazz.newInstance();
                handlerMap.put(handler.getCmd(), handler);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static  AbstractRespHandler getHandlerMap(int cmd) {
        return handlerMap.get(cmd);
    }
}
