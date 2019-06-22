package com.yuandian.server.logic.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;
import com.yuandian.data.message.PAuth;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;
import com.yuandian.server.logic.user.UserService;

/**
 * 认证
 *
 * @author twjitm 2019/4/9/15:57
 */
@MessageAnnotation(cmd = MessageCmd.AUTH_ACCOUNT)
public class AUTH extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PAuth pLogin = PAuth.parseFrom(bytes);
            UserInfo user = new UserInfo(client.getChannel());
            String token=pLogin.getToken();
            long uid=pLogin.getUid();
            String deviceId=pLogin.getDeviceId();
            boolean result = UserService.checkUserToken(uid, deviceId, token);
            if (!result) {
                client.writeErrorData(cmd, ErrorCode.AUTH_ID_ERROR);
                return;
            }
            user.setUid(pLogin.getUid());
            IoClientManager.put(user);
            PUserInfo.Builder puserInfo = PUserInfo.newBuilder();
            puserInfo.setUid((int) user.getUid());
            user.writeData(cmd, puserInfo.build().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


    }
}
