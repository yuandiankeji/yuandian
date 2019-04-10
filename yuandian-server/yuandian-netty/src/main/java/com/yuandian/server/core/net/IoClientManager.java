package com.yuandian.server.core.net;

import com.yuandian.server.logic.user.UserInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author twjitm 2019/4/9/14:56
 */
public class IoClientManager {
    private static Map<Long, UserInfo> onLineSession = new ConcurrentHashMap<>();


    public static void put(UserInfo userInfo) {
        onLineSession.putIfAbsent(userInfo.getUid(), userInfo);
    }

    public static UserInfo getOnlineUser(long uid) {
        return onLineSession.get(uid);
    }

    public static UserInfo getUserInfo(long uid) {
        UserInfo userInfo = onLineSession.get(uid);
        if (userInfo == null) {
            //db
        }
        return userInfo;
    }

    public boolean createUSer(long uid) {
        //TODO 对接server
        return true;
    }

}
