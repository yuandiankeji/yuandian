package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PGetChatRecord;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;

public class GetChatRecord extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PGetChatRecord getChatRecord = PGetChatRecord.parseFrom(bytes);
            UserInfo userInfo = IoClientManager.getUserInfo(client);
            long targetId = getChatRecord.getTargetId();
            long limit = getChatRecord.getLimit();
              long  uid=userInfo.getUid();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
