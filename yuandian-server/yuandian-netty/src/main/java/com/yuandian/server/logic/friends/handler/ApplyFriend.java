package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PApplyFriend;
import com.yuandian.data.push.PushFriendApply;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 好友申请
 */
@MessageAnnotation(cmd = MessageCmd.APPLY_FRIEND)
public class ApplyFriend extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(ApplyFriend.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        PApplyFriend applyFriend = null;
        try {
            applyFriend = PApplyFriend.parseFrom(bytes);
            logger.debug("cmd={},target={}", cmd, applyFriend.getTargetId());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        if (applyFriend == null) {
            userInfo.writeData(cmd, ErrorCode.USER_INFO_ERROR);
            return;
        }
        long uid = userInfo.getUid();
        long targetId = applyFriend.getTargetId();
        SpringBeanFactory.getInstance().getFriendService().apply(uid, targetId);
        UserInfo targetUserInfo = IoClientManager.getUserInfo(targetId);
        PushFriendApply.Builder push = PushFriendApply.newBuilder();
        push.setTargetId(targetId);
        targetUserInfo.writeData(MessageCmd.PUSH_MESSAGE_CMD.PUSH_APPLY_FRIEND, push.build().toByteArray());
        userInfo.writeData(cmd);
    }
}
