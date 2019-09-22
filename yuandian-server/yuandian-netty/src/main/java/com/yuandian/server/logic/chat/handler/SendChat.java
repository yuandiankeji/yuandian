package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.common.ErrorCode;
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
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送聊天消息
 *
 * @author twjitm 2019/4/16/23:49
 */
@MessageAnnotation(cmd = MessageCmd.SEND_CHAT)
public class SendChat extends AbstractTcpHandler {

    private Logger logger = LoggerFactory.getLogger(SendChat.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        //服务器收到客户端发送过来的消息
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        try {
            PSendChat pSendChat = PSendChat.parseFrom(bytes);

            long uid = userInfo.getUid();
            PChatInfo pChat = pSendChat.getChat();
            long toUid = pChat.getTargetUid();
            int chatType = pChat.getType();
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
            boolean isBan = friendService.isban(uid, toUid);
            if (isBan) {
                userInfo.writeData(cmd, ErrorCode.BAN_USER_SHEND_MESSAGE_ERROR);
                return;
            }
            UserInfo targetUser = IoClientManager.getOnlineUser(toUid);
            ChatPo chatPo = new ChatPo();
            PushChatMessage.Builder pushChatMessage = PushChatMessage.newBuilder();
            long mId = ZDateUtils.getNow();
            PChatInfo.Builder builder = PChatInfo.newBuilder();
            builder.setMid(mId);
            builder.setContext(pChat.getContext());
            builder.setCTime(ZDateUtils.getNow());
            builder.setTargetUid(toUid);
            builder.setType(chatType);
            builder.setIsRead(0);
            builder.setUid(uid);
            pushChatMessage.setChatInfo(builder.build());
            //往别的客户端推送消息,暂时不考虑分布式
            chatPo.setUid(uid);
            chatPo.setTargetId(toUid);
            chatPo.setIsread(0);
            chatPo.setCtime(ZDateUtils.getNow());
            chatPo.setContext(pChat.getContext());
            chatPo.setMid(mId);
            chatPo.setType(chatType);
            boolean targetOnline = false;
            if (targetUser != null) {
                targetUser.writeData(MessageCmd.PushMessageCmd.PUSH_CHAT, pushChatMessage.build().toByteArray());
                targetOnline = true;
            }
            chatService.saveChat(chatPo, targetOnline);
            //保存消息

            userInfo.writeData(cmd, builder.build().toByteArray());

            logger.info("[SendChat] | cmd={},data={}", cmd, pSendChat.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            logger.error("[SendChat] | cmd={}", cmd);
            userInfo.writeData(ErrorCode.SYS_PROTO_TYPE_ERROR);
        }

    }
}
