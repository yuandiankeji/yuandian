package com.yuandian.server.core.net;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author twjitm 2019/4/6/21:06
 */
public final class MessageCmd {
    /**
     * 请求消息号
     */
    public static final int REQUEST_BASE = 1000;
    public static final int GET_USER_INFO = REQUEST_BASE + 1;
    public static final int AUTH_ACCOUNT = REQUEST_BASE + 2;
    public static final int LOGIN = REQUEST_BASE + 3;
    public static final int SEND_CHAT = REQUEST_BASE + 4;
    public static final int GET_CHAT_RECORD = REQUEST_BASE + 5;
    public static final int DELETE_CHAT_RECORD = REQUEST_BASE + 6;

    public static final int ALL_FRIEND_APPLY_LIST = REQUEST_BASE + 20;//申请列表
    public static final int APPLY_FRIEND = REQUEST_BASE + 21;// 申请好友
    public static final int AGREE_FRIEND = REQUEST_BASE + 22;//同意
    public static final int REFUSE_FRIEND = REQUEST_BASE + 23;//拒绝
    public static final int ALL_FRIEND_LIST = REQUEST_BASE + 24;//好友列表
    public static final int DELETE_FRIEND = REQUEST_BASE + 25;//删除好友



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
