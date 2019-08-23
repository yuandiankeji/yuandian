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
    //有多少条未读的消息
    String NOT_READ_CHAT_MESSAGE_NUM = "incr:chat:not:read:num:%s:%s";
    //聊天好友列表
    String CHAT_USER_LIST = "h:chat:user:list:%s";

    //用户信息
    String USER_INFO = "h:user:info";

    //好友列表
    String USER_FRIEND_LIST = "h:friend:list:%s";

    //申请列表 目标人uid
    String FRIEND_APPLY_LIST = "h:apply:friend:list:%s";

    //屏蔽好友列表
    String BAN_FRIEND_LIST = "s:ban:friend:list:%s";
}
