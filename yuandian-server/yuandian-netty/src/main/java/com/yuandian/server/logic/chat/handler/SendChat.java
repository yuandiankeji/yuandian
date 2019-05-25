package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PChatInfo;
import com.yuandian.data.message.PSendChat;
import com.yuandian.data.push.PushChatMessage;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.core.net.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.user.UserInfo;

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

            UserInfo targetUser = IoClientManager.getOnlineUser(toUid);
            if (targetUser != null) {
                PushChatMessage.Builder pushChatMessage = PushChatMessage.newBuilder();
                pushChatMessage.setChatInfo(pChat);
                //往别的客户端推送消息,暂时不考虑分布式

                targetUser.writeData(MessageCmd.PUSH_MESSAGE_CMD.PUSH_CHAT, pushChatMessage.build().toByteArray());
            }
            //保存消息
            //Todo


        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
