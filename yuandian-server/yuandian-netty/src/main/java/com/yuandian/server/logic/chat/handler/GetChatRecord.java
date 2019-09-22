package com.yuandian.server.logic.chat.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.core.utils.ZDateUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@MessageAnnotation(cmd = MessageCmd.GET_CHAT_RECORD)
public class GetChatRecord extends AbstractTcpHandler {
    Logger logger = LoggerFactory.getLogger(GetChatRecord.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        try {
            PGetChatRecord getChatRecord = PGetChatRecord.parseFrom(bytes);
            logger.info("[GetChatRecord] | cmd={},data={}", cmd, getChatRecord.toString());
            UserInfo userInfo = IoClientManager.getUserInfo(client);
            long targetId = getChatRecord.getTargetId();
            long limit = getChatRecord.getLimit();
            long uid = userInfo.getUid();
            long footMId = getChatRecord.getFootMId();
            if (footMId == 0) {
                footMId = ZDateUtils.getNow();
            }
            ChatService chatService = SpringBeanFactory.getInstance().getChatService();
            List<ChatPo> chatPoList = chatService.getChatInfo(uid, targetId, 0, footMId, 10);

            PChatInfos.Builder pbs = PChatInfos.newBuilder();
            chatPoList.forEach(po -> {
                PChatInfo.Builder pb = PChatInfo.newBuilder();
                pb.setUid(po.getUid());
                pb.setTargetUid(po.getTargetId());
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
            logger.error("[GetChatRecord] | cmd={}", cmd);
        }

    }
}
