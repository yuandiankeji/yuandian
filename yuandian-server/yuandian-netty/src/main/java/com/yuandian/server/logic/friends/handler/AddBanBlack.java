package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.data.message.PAddToBanBlack;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.UserInfo;

/**
 * 添加黑名单
 */
@MessageAnnotation(cmd = MessageCmd.SHIELD_USER)
public class AddBanBlack extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        PAddToBanBlack pAddToBanBlack = null;
        try {
            pAddToBanBlack = PAddToBanBlack.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            return;
        }
        long targetId = pAddToBanBlack.getTargetId();
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        friendService.addBlackList(userInfo.getUid(), targetId);
        userInfo.writeData(cmd);
    }
}
