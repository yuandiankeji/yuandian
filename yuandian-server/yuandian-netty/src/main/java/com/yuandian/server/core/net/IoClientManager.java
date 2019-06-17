package com.yuandian.server.core.net;

import com.yuandian.server.logic.user.UserInfo;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author twjitm 2019/4/9/14:56
 */
public class IoClientManager {
    private static final AttributeKey<Long> SESSION_CLIENT_ID = AttributeKey.valueOf("SESSION_CLIENT_ID");
    private static Map<Long, UserInfo> onLineSession = new ConcurrentHashMap<>();


    public static void put(UserInfo userInfo) {

        userInfo.getChannel().attr(SESSION_CLIENT_ID).set(userInfo.getUid());
        UserInfo userOld = getOnlineUser(userInfo.getUid());
        if (userOld != null) {
            //强制客户端下线
            onLineSession.remove(userOld.getUid());
            userOld.getChannel().close();
        }
        onLineSession.putIfAbsent(userInfo.getUid(), userInfo);
    }

    public static UserInfo getOnlineUser(long uid) {
        return onLineSession.get(uid);
    }

    public static UserInfo getUserInfo(IoClient client) {
        long uid = client.getChannel().attr(SESSION_CLIENT_ID).get();
        return getUserInfo(uid);
    }

    public static UserInfo getUserInfo(long uid) {
        UserInfo userInfo = getOnlineUser(uid);
        if (userInfo == null) {
            //db
        }
        return userInfo;
    }

    public static void remove(Channel channel) {
        if (channel != null) {
            long uid = channel.attr(SESSION_CLIENT_ID).get();
            onLineSession.remove(uid);
        }
    }


}
