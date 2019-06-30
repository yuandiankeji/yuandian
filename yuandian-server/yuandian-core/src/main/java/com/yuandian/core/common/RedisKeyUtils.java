package com.yuandian.core.common;

public class RedisKeyUtils {

    public static String getFriendApplyListKey(long uid) {
        return String.format(Rediskey.FRIEND_APPLY_LIST, uid);
    }

    public static String getBlackListKey(long uid) {
        return String.format(Rediskey.BAN_FRIEND_LIST,uid);
    }
}
