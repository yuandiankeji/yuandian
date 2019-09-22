package com.yuandian.core.common;

public class RedisKeyUtils {

    /**
     * 聊天列表
     *
     * @param uid
     * @param targetId
     * @return
     */
    public static String getChatInfoListKey(long uid, long targetId) {
        return String.format(Rediskey.CHAT_MESSAGE_INFO_LIST, getChatMainKey(uid, targetId));
    }

    private static String getChatMainKey(long uid, long targetId) {
        String token;

        if (uid > targetId) {
            token = uid + ":" + targetId;
        } else {
            token = targetId + ":" + uid;
        }
        return token;
    }

    /**
     * 当前人1-有多少条人2的未读消息
     *
     * @param uid
     * @param targetId
     * @return
     */
    public static String getNotReadChatNum(long uid, long targetId) {
        return String.format(Rediskey.NOT_READ_CHAT_MESSAGE_NUM, uid, targetId);
    }

    /**
     * 申请列表
     *
     * @param uid
     * @return
     */
    public static String getFriendApplyListKey(long uid) {
        return String.format(Rediskey.FRIEND_APPLY_LIST, uid);
    }

    /**
     * 黑名单列表
     *
     * @param uid
     * @return
     */
    public static String getBlackListKey(long uid) {
        return String.format(Rediskey.BAN_FRIEND_LIST, uid);
    }

    /**
     * 好友列表
     *
     * @param uid
     * @return
     */
    public static String getFriendListKey(long uid) {
        return String.format(Rediskey.USER_FRIEND_LIST, uid);
    }
}
