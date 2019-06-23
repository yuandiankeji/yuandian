package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PDeleteChat;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;

@MessageAnnotation(cmd = MessageCmd.DELETE_CHAT_RECORD)
public class DeleteChat extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

        try {
            UserInfo userInfo = IoClientManager.getUserInfo(client);

            PDeleteChat builder = PDeleteChat.parseFrom(bytes);
            long targetId = builder.getTargetId();
            long mId = builder.getMId();
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            chatService.delete(userInfo.getUid(), targetId, mId);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
