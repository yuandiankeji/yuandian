package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.data.message.PDeleteFriend;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;

@MessageAnnotation(cmd = MessageCmd.DELETE_FRIEND)
public class DeleteFriend extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PDeleteFriend pDeleteFriend = null;
        try {
            pDeleteFriend = PDeleteFriend.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }
        SpringBeanFactory.getInstance().getFriendService().deleteFriend(uid, pDeleteFriend.getTargetId());

        userInfo.writeData(cmd, ErrorCode.SYS_SUCCESS);

    }
}
