package com.yuandian.server.logic.login;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;
import com.yuandian.data.message.PAuth;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.logic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 认证
 *
 * @author twjitm 2019/4/9/15:57
 */
@MessageAnnotation(cmd = MessageCmd.AUTH_ACCOUNT)
public class AUTH extends AbstractTcpHandler {

    Logger logger = LoggerFactory.getLogger(AUTH.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PAuth pLogin = PAuth.parseFrom(bytes);
            logger.info("[AUTH] | cmd={},data={}", cmd, pLogin.toString());
            UserInfo user = new UserInfo(client.getChannel());
            String token = pLogin.getToken();
            long uid = pLogin.getUid();
            String deviceId = pLogin.getDeviceId();
            UserPo userPo = SpringBeanFactory.getInstance().getUserService().getUserInfo(uid);
            if (userPo == null) {
                client.writeData(cmd, ErrorCode.AUTH_ID_ERROR);
                return;
            }
            //TODO session auth
            user.setUid(pLogin.getUid());
            user.setDevice(deviceId);
            user.setOpenId(token);
            IoClientManager.put(user);
            PUserInfo.Builder puserInfo = PUserInfo.newBuilder();
            puserInfo.setUid((int) user.getUid());
            user.writeData(cmd, puserInfo.build().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            client.writeData(ErrorCode.SYS_PROTO_TYPE_ERROR);
        }


    }
}
