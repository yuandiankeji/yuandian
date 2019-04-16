package com.yuandian.server.logic.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;
import com.yuandian.data.message.PLogin;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;

/**
 * 认证
 *
 * @author twjitm 2019/4/9/15:57
 */
@MessageAnnotation(cmd = 3)
public class AUTH extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PLogin pLogin = PLogin.parseFrom(bytes);
            UserInfo user = new UserInfo(client.getChannel());
            user.setUid(pLogin.getUid());
            IoClientManager.put(user);
            PUserInfo.Builder puserInfo = PUserInfo.newBuilder();
            puserInfo.setUid((int) user.getUid());
            client.writeData(cmd, puserInfo.build().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }


    }
}
