package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.message.PReadChat;
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

/**
 * 阅读消息
 *
 * @author twjitm 2019/4/17/0:03
 */
@MessageAnnotation(cmd = MessageCmd.READ_CHAT)
public class ReadChat extends AbstractTcpHandler {
    Logger logger = LoggerFactory.getLogger(ReadChat.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        PReadChat builder = null;
        try {
            builder = PReadChat.parseFrom(bytes);
            logger.info("[ReadChat] | cmd={},data={}", cmd, builder.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            logger.error("[ReadChat] | cmd={}", cmd);
            return;
        }
        chatService.read(userInfo.getUid(), builder.getTargetId());
        userInfo.writeData(cmd);
    }
}
