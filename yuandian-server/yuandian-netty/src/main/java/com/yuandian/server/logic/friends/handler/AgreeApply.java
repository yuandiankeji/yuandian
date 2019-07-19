package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.core.common.ResultObject;
import com.yuandian.data.message.PAgreeApply;
import com.yuandian.data.push.PushFriendAgree;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.consts.ApplyConst;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.UserInfo;

/**
 * 同意好友申请
 */
@MessageAnnotation(cmd = MessageCmd.AGREE_FRIEND)
public class AgreeApply extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PAgreeApply pAgreeApply;
        try {
            pAgreeApply = PAgreeApply.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            return;
        }
        long targetUid = pAgreeApply.getTargetId();
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        ResultObject<Integer> result = friendService.addFriend(uid, targetUid);
        friendService.applyOption(uid, targetUid, ApplyConst.APPLY_AGREE.getCode());
        if (!result.success()) {
            userInfo.writeData(cmd, ErrorCode.AUTH_ID_ERROR);
            return;
        }
        PushFriendAgree.Builder push = PushFriendAgree.newBuilder();
        push.setTargetId(uid);
        UserInfo targetUser = IoClientManager.getUserInfo(targetUid);
        targetUser.writeData(MessageCmd.PushMessageCmd.PUSH_APPLY_AGREE, push.build().toByteArray());
        userInfo.writeData(cmd, result.getCode());
    }
}
