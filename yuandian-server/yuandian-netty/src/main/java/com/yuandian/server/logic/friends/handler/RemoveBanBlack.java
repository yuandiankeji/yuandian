package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.data.common.PResultInfoEx;
import com.yuandian.data.message.PAddToBanBlack;
import com.yuandian.data.message.PRemoveBanBlack;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 移除黑名单
 */
@MessageAnnotation(cmd = MessageCmd.REMOVE_BLACK)
public class RemoveBanBlack extends AbstractTcpHandler {

    private Logger logger = LoggerFactory.getLogger(RemoveBanBlack.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        PRemoveBanBlack pAddToBanBlack = null;
        try {
            pAddToBanBlack = PRemoveBanBlack.parseFrom(bytes);
            logger.info("[RemoveBanBlack] | cmd={},data={}", cmd, pAddToBanBlack.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            logger.error("[RemoveBanBlack] | up proto failed，cmd={}", cmd);
            return;
        }
        long target = pAddToBanBlack.getTargetId();
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        friendService.removeBlack(userInfo.getUid(), target);
        PResultInfoEx.Builder builder = PResultInfoEx.newBuilder();
        builder.setTargetId(target);
        userInfo.writeData(cmd, builder.build().toByteArray());

    }
}
