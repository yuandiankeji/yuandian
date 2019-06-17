package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PApplyFriend;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.core.net.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@MessageAnnotation(cmd = MessageCmd.ALL_FRIEND_LIST)
public class AllFriendsList extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(AllFriendsList.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);

        long uid = userInfo.getUid();
        SpringBeanFactory.getInstance().getFriendService().getApplyList(uid);

    }
}
