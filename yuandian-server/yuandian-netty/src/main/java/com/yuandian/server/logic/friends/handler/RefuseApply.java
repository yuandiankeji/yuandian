package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.ResultObject;
import com.yuandian.data.message.PRefuseApply;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;

/**
 * 拒绝好友申请
 */
public class RefuseApply extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PRefuseApply pRefuseApply = null;
        try {
            pRefuseApply = PRefuseApply.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            return;
        }
        long targetId = pRefuseApply.getTargetId();
        ResultObject<Integer> resultObject =
                SpringBeanFactory.getInstance().getFriendService().refuseApply(uid, targetId);
        userInfo.writeData(cmd, resultObject.getCode());

    }
}






