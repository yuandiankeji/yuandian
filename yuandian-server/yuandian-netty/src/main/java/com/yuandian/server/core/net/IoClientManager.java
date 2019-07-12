package com.yuandian.server.core.net;

import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.logic.user.service.UserService;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author twjitm 2019/4/9/14:56
 */
public class IoClientManager {
    private static Logger logger = LoggerFactory.getLogger(IoClientManager.class);
    private static final AttributeKey<Long> SESSION_CLIENT_ID = AttributeKey.valueOf("SESSION_CLIENT_ID");
    private static volatile Map<Long, UserInfo> onLineSession = new ConcurrentHashMap<>();


    public static void put(UserInfo userInfo) {

        userInfo.getChannel().attr(SESSION_CLIENT_ID).set(userInfo.getUid());
        UserInfo userOld = getOnlineUser(userInfo.getUid());
        if (userOld != null) {
            //强制客户端下线
            onLineSession.remove(userOld.getUid());
            userOld.getChannel().close();
        }
        onLineSession.putIfAbsent(userInfo.getUid(), userInfo);
        logger.info("[Session] current online user num={}", onLineSession.size());
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
            UserService userService = SpringBeanFactory.getInstance().getUserService();
            UserPo userPo = userService.getUserInfo(uid);
            userInfo = new UserInfo(null);
            userInfo.setUid(uid);
            userInfo.setDevice(userPo.getAccount());
            userInfo.setOpenId(userPo.getAccount());
        }
        return userInfo;
    }

    public static void remove(Channel channel) {
        if (channel != null) {
            Attribute<Long> attr = channel.attr(SESSION_CLIENT_ID);
            if (attr != null) {
                Long uid = attr.get();
                if (uid != null) {
                    logger.info("[IoClient] client close");
                    onLineSession.remove(uid);
                    logger.info("[Session] current online user num={}", onLineSession.size());
                }

            }

        }
    }


}
