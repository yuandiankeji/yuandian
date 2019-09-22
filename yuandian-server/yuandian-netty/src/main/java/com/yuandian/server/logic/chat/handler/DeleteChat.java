package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.data.message.PDeleteChat;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageAnnotation(cmd = MessageCmd.DELETE_CHAT_RECORD)
public class DeleteChat extends AbstractTcpHandler {

    Logger logger = LoggerFactory.getLogger(DeleteChat.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

        UserInfo userInfo = IoClientManager.getUserInfo(client);
        try {
            PDeleteChat builder = PDeleteChat.parseFrom(bytes);
            logger.info("[DeleteChat] | cmd={},data={}", cmd, builder.toString());
            long targetId = builder.getTargetId();
            long mId = builder.getMId();
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            chatService.delete(userInfo.getUid(), targetId, mId);
            userInfo.writeData(ErrorCode.SYS_SUCCESS);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            logger.error("[DeleteChat] | cmd={}", cmd);
            userInfo.writeData(ErrorCode.SYS_SUCCESS);
        }
    }
}
