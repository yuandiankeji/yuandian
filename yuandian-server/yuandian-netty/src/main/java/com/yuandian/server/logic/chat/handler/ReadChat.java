package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PReadChat;
import com.yuandian.data.push.PushChatRead;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;

/**
 * @author twjitm 2019/4/17/0:03
 */
@MessageAnnotation(cmd = MessageCmd.READ_CHAT)
public class ReadChat extends AbstractTcpHandler {

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        PReadChat builder = null;
        try {
            builder = PReadChat.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }
        long mid = chatService.read(userInfo.getUid(), builder.getTargetId());
        UserInfo targetUserInfo = IoClientManager.getUserInfo(builder.getTargetId());
        PushChatRead.Builder pb = PushChatRead.newBuilder();
        pb.setTargetId(userInfo.getUid());
        pb.setChatId(mid);
        targetUserInfo.writeData(MessageCmd.PushMessageCmd.PUSH_CHAT, pb.build().toByteArray());
    }
}
