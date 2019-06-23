package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.data.common.PChatUserListInfos;
import com.yuandian.data.message.PGetChatUserList;
import com.yuandian.entity.UserPO;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.utils.ObjectPoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 好友聊天列表
 */
@MessageAnnotation(cmd = MessageCmd.GET_CHAT_USER_LIST)
public class GetChatUserList extends AbstractTcpHandler {
    Logger logger = LoggerFactory.getLogger(GetChatUserList.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        PGetChatUserList pGetChatUserList = null;
        try {
            pGetChatUserList = PGetChatUserList.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            userInfo.writeData(cmd, ErrorCode.SYS_PROTO_TYPE_ERROR);
            e.printStackTrace();
            return;
        }
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        List<UserPO> userPOList = chatService.getChatUserInfo(uid);
        PChatUserListInfos chatUserList = ObjectPoUtils.getPChatUserListInfos(userPOList);
        userInfo.writeData(cmd, chatUserList.toByteArray());

    }
}
