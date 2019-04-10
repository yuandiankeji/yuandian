package com.yuandian.server.logic;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;
import com.yuandian.data.message.PGetUserInfo;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.MessageCmd;

/**
 * @author twjitm 2019/4/6/21:05
 */
@MessageAnnotation(cmd = MessageCmd.GET_USER_INFO)
public class GetUserUserInfo extends AbstractTcpHandler {

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PGetUserInfo builder = PGetUserInfo.parseFrom(bytes);
            long uid = builder.getUserId();
            String name = builder.getName();
            PUserInfo.Builder puserInfo = PUserInfo.newBuilder();
            puserInfo.setName(name);
            puserInfo.setUid((int) uid);
            puserInfo.setOpenid("1111111111");
            client.writeData(cmd, PUserInfo.newBuilder().build().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
