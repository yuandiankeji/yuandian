package com.yuandian.server.logic.user.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.data.common.PUserBaseInfo;
import com.yuandian.data.message.PGetUserInfo;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.utils.ObjectPoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twjitm 2019/4/6/21:05
 */
@MessageAnnotation(cmd = MessageCmd.GET_USER_INFO)
public class GetUserUserInfo extends AbstractTcpHandler {
    Logger logger = LoggerFactory.getLogger(GetUserUserInfo.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        PGetUserInfo pGetUserInfo = null;
        UserInfo user = IoClientManager.getUserInfo(client);

        try {
            pGetUserInfo = PGetUserInfo.parseFrom(bytes);
            logger.info("[GetUserUserInfo] | cmd={},data={}", cmd, pGetUserInfo.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            user.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            logger.error("[GetUserUserInfo] | cmd={}", cmd);
            return;
        }

        UserPo userInfo = SpringBeanFactory.getInstance().getUserService().
                getUserInfo(pGetUserInfo.getTargetId());
        PUserBaseInfo builder = ObjectPoUtils.getPUserBaseInfo(user.getUid(), userInfo);
        user.writeData(cmd, builder.toByteArray());

    }
}
