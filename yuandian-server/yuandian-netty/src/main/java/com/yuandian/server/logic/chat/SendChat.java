package com.yuandian.server.logic.chat;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;
import com.yuandian.data.message.PSendChat;
import com.yuandian.data.push.PushChatMessage;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;

/**
 * @author twjitm 2019/4/16/23:49
 */
@MessageAnnotation(cmd = 4)
public class SendChat extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        //服务器收到客户端发送过来的消息
        try {
            PSendChat pSendChat = PSendChat.parseFrom(bytes);
            long uid = pSendChat.getUid();
            long toUid = pSendChat.getToUid();
            String context = pSendChat.getContext();
            UserInfo touser = IoClientManager.getOnlineUser(toUid);
            if (touser != null) {
                PushChatMessage.Builder pushChatMessage = PushChatMessage.newBuilder();
                pushChatMessage.setSrcUid(uid);
                pushChatMessage.setContext(context);
                pushChatMessage.setTargetUId(toUid);
                touser.writeData((short) 1000, pushChatMessage.build().toByteArray());
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
