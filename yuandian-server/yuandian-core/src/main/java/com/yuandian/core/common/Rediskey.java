package com.yuandian.core.common;

/**
 * @author: luyufeng
 * @Date: 2019/4/5
 */

public interface Rediskey {
    String TOKEN = "token#";
    //测试
    String LOGIN_USER = "login_user";
    String ALLUSER = "alluser";
    //测试

    //短信验证码
    String MSG_CODE = "phone_msg#";
    //聊天信息保存
    String CHAT_MESSAGE_INFO_LIST = "h:chat:message:%s";
    //聊天好友列表
    String CHAT_USER_LIST = "h:chat:user:list:%s";
}
