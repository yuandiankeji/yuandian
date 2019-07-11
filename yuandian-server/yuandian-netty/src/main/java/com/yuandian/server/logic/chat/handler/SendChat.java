package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.data.common.PChatInfo;
import com.yuandian.data.message.PSendChat;
import com.yuandian.data.push.PushChatMessage;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;

/**
 * @author twjitm 2019/4/16/23:49
 */
@MessageAnnotation(cmd = MessageCmd.SEND_CHAT)
public class SendChat extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        //服务器收到客户端发送过来的消息
        try {
            PSendChat pSendChat = PSendChat.parseFrom(bytes);
            UserInfo userInfo = IoClientManager.getUserInfo(client);
            long uid = userInfo.getUid();
            PChatInfo pChat = pSendChat.getChat();
            long toUid = pChat.getTargetUid();
            int chatType = pChat.getType();
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            UserInfo targetUser = IoClientManager.getOnlineUser(toUid);
            ChatPo chatPo = new ChatPo();

            PushChatMessage.Builder pushChatMessage = PushChatMessage.newBuilder();
            pushChatMessage.setChatInfo(pChat);
            //往别的客户端推送消息,暂时不考虑分布式
            chatPo.setUid(uid);
            chatPo.setTargetId(toUid);
            chatPo.setIsread(0);
            chatPo.setCtime(ZDateUtils.getSeconds());
            chatPo.setContext(pChat.getContext());
            chatPo.setMid(ZDateUtils.now().getTime());
            chatPo.setType(chatType);
            boolean targetOnline = false;
            if (targetUser != null) {
                targetUser.writeData(MessageCmd.PushMessageCmd.PUSH_CHAT, pushChatMessage.build().toByteArray());
                targetOnline = true;
            }
            chatService.saveChat(chatPo,targetOnline);
            //保存消息

            userInfo.writeData(cmd, pChat.toByteArray());

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
