package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.core.common.ResultObject;
import com.yuandian.data.message.PRemoveUserInChatList;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;

/**
 * 重聊天列表里面移除
 */
@MessageAnnotation(cmd = MessageCmd.DELETE_CHAT_USER_LIST)
public class DeleteChatUserList extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long targetId = 0;

        try {
            PRemoveUserInChatList builder = PRemoveUserInChatList.parseFrom(bytes);
            targetId = builder.getTargetId();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            userInfo.writeData(ErrorCode.SYS_PROTO_TYPE_ERROR);
            return;
        }
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        ResultObject<Integer> result = chatService.removeChatUser(userInfo.getUid(), targetId);
        userInfo.writeData(cmd, result.getCode());

    }
}
