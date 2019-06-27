package com.yuandian.client.handler;


import com.yuandian.client.net.IoMessage;


/**
 * 所有的返回接口都必须实现这个handler方法
 *
 * @author twjitm 2019/4/15/23:18
 */
public abstract class AbstractRespHandler {
    private static int STATUS_OK = 200;
    private int cmd;

    public AbstractRespHandler(int cmd) {
        this.cmd = cmd;
    }

    public int getCmd() {
        return cmd;
    }

    /**
     * 消息返回通过处理
     * @param message
     * @return
     */
    public boolean verification(IoMessage message) {
        int status = message.getStatus();
        return status == STATUS_OK;
    }

    public abstract void handler(IoMessage message);
}
