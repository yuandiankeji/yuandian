package com.yuandian.core.common;

public interface ErrorCode {
    /**
     *
     */
   short SYS_SUCCESS=200;
   short SYS_SERVER_ERROR=500;
    /**
     * 上行协议错误
     */
   short SYS_PROTO_TYPE_ERROR=501;


    /**
     * 用户认证失败
     */
    short AUTH_ID_ERROR = 10001;
    /**
     * 用户信息错误
     */
    short USER_INFO_ERROR = 10002;

}
