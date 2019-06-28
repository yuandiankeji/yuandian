package com.yuandian.core.common;

public class RedisKeyUtils {

    public static String getFriendApplyListKey(long uid) {
        return String.format(Rediskey.FRIEND_APPLY_LIST, uid);
    }
}
