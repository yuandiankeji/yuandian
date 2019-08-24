package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.data.message.PDeleteFriend;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 删除好友
 */
@MessageAnnotation(cmd = MessageCmd.DELETE_FRIEND)
public class DeleteFriend extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(DeleteFriend.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PDeleteFriend pDeleteFriend = null;
        try {
            pDeleteFriend = PDeleteFriend.parseFrom(bytes);
            logger.info("[DeleteFriend] | cmd={},data={}", cmd, pDeleteFriend.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_SERVER_ERROR);
            logger.error("[DeleteFriend] | cmd={}", cmd);
            return;
        }
        SpringBeanFactory.getInstance().getFriendService().deleteFriend(uid, pDeleteFriend.getTargetId());

        userInfo.writeData(cmd, ErrorCode.SYS_SUCCESS);

    }
}
