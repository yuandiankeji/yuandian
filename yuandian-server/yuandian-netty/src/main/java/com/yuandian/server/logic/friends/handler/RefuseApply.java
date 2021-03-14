package com.yuandian.server.logic.friends.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.core.common.ResultObject;
import com.yuandian.data.common.PResultInfoEx;
import com.yuandian.data.message.PRefuseApply;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拒绝好友申请
 */
@MessageAnnotation(cmd = MessageCmd.REFUSE_FRIEND)
public class RefuseApply extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(RefuseApply.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PRefuseApply pRefuseApply = null;
        try {
            pRefuseApply = PRefuseApply.parseFrom(bytes);
            logger.info("[RefuseApply] | cmd={},data={}", cmd, pRefuseApply.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            logger.error("[RefuseApply] | up proto failed，cmd={}", cmd);
            return;
        }
        long targetId = pRefuseApply.getTargetId();
        ResultObject<Integer> resultObject =
                SpringBeanFactory.getInstance().getFriendService().refuseApply(uid, targetId);
        PResultInfoEx.Builder builder = PResultInfoEx.newBuilder();
        builder.setTargetId(targetId);
        userInfo.writeData(cmd, builder.build().toByteArray());

    }
}






