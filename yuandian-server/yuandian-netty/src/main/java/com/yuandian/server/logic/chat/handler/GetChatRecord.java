package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PChatInfo;
import com.yuandian.data.common.PChatInfos;
import com.yuandian.data.message.PGetChatRecord;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.UserInfo;

import java.util.List;

@MessageAnnotation(cmd = MessageCmd.GET_CHAT_RECORD)
public class GetChatRecord extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PGetChatRecord getChatRecord = PGetChatRecord.parseFrom(bytes);
            UserInfo userInfo = IoClientManager.getUserInfo(client);
            long targetId = getChatRecord.getTargetId();
            long limit = getChatRecord.getLimit();
            long uid = userInfo.getUid();
            long footMId = getChatRecord.getFootMId();
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            List<ChatPo> chatPoList = chatService.getChatInfo(uid, targetId, footMId, limit, 10);

            PChatInfos.Builder pbs = PChatInfos.newBuilder();
            chatPoList.forEach(po -> {
                PChatInfo.Builder pb = PChatInfo.newBuilder();
                pb.setUid(uid);
                pb.setTargetUid(targetId);
                pb.setType(po.getType());
                pb.setMid(po.getMid());
                pb.setCTime(po.getCtime());
                pb.setContext(po.getContext());
                pb.setIsRead(po.getIsread());
                pbs.addChatInfo(pb);
            });
            userInfo.writeData(cmd, pbs.build().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
