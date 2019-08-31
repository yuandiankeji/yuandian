package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.data.message.PDeleteAllChat;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 清理所有聊天记录
 */
@MessageAnnotation(cmd = MessageCmd.DELETE_CHAT_ALL_RECORD)
public class DeleteAllChat extends AbstractTcpHandler {

    private Logger logger = LoggerFactory.getLogger(DeleteAllChat.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {

        UserInfo userInfo = IoClientManager.getUserInfo(client);
        PDeleteAllChat pDeleteAllChat = null;
        try {
            pDeleteAllChat = PDeleteAllChat.parseFrom(bytes);
            logger.info("[DeleteAllChat] | cmd={},data={}", cmd, pDeleteAllChat.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(cmd, ErrorCode.SYS_SERVER_ERROR);
            logger.error("[DeleteAllChat] | cmd={}", cmd);
            return;
        }

        long targetUid = pDeleteAllChat.getTargetId();
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        chatService.delete(userInfo.getUid(), targetUid);
        userInfo.writeData(ErrorCode.SYS_SUCCESS);
    }
}
