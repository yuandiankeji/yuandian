package com.yuandian.server.core.net;

/**
 * @author twjitm 2019/4/6/21:06
 */
public final class MessageCmd {
    /**
     * 请求消息号
     */
    public static final int REQUEST_BASE = 1000;
    /**
     * 用户信息
     */
    public static final int GET_USER_INFO = REQUEST_BASE + 1;
    /**
     * 登陆认证
     */
    public static final int AUTH_ACCOUNT = REQUEST_BASE + 2;
    /**
     * todo 暂时不用（废弃）
     */
    public static final int LOGIN = REQUEST_BASE + 3;
    /**
     * 发送消息
     */
    public static final int SEND_CHAT = REQUEST_BASE + 4;
    /**
     * 获得聊天消息列表
     */
    public static final int GET_CHAT_RECORD = REQUEST_BASE + 5;
    /**
     * 删除聊天消息
     */
    public static final int DELETE_CHAT_RECORD = REQUEST_BASE + 6;
    /**
     * 阅读消息
     */
    public static final int READ_CAHT = REQUEST_BASE + 7;
    /**
     * 聊天好友列表
     */
    public static final int GET_CHAT_USER_LIST = REQUEST_BASE + 8;
    /**
     * 推出登陆
     */
    public static final int LOGOUT = REQUEST_BASE + 9;




    /**
     * 推送消息号
     */
    public final static class PUSH_MESSAGE_CMD {
        static final int PUSH_BASE = 9999;
        public static final short PUSH_CHAT = PUSH_BASE + 1;
    }

    /**
     * rpc 消息号
     */
    public final static class RCP_MESSAGE_CMD {
        static final int RPC_BASE = 19999;


    }
}
