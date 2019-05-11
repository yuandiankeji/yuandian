package com.yuandian.client.handler;


import com.yuandian.client.net.IoMessage;

/**
 * 所有的返回接口都必须实现这个handler方法
 *
 * @author twjitm 2019/4/15/23:18
 */
public abstract class AbstractRespHandler {
    private int cmd;

    public AbstractRespHandler(int cmd) {
        this.cmd = cmd;
    }

    public int getCmd() {
        return cmd;
    }

    public abstract void handler(IoMessage message);
}
