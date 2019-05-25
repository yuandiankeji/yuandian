package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.DateConstants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.server.config.RedisFactory;
import com.yuandian.server.logic.chat.entity.ChatPo;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public void saveChat(ChatPo chatPo) {
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_MESSAGE_INFO_LIST, getChatMainKey(chatPo.getUid(), chatPo.getTargetId()));
        chatRedis.hsetString(key, chatPo.getMid() + "", chatPo.serialize(), DateConstants.SECOND);
    }

    @Override
    public List<ChatPo> getChatInfo(long uid, long targetId, int limit) {
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_MESSAGE_INFO_LIST, getChatMainKey(uid, targetId));
        Map<String, String> map = chatRedis.hgetAll(key);
        List<ChatPo> list = new ArrayList<>();
        map.forEach((k, v) -> {
            ChatPo po = new ChatPo();
            po = (ChatPo) po.deserialize(v);
            list.add(po);
        });

        return list;
    }

    @Override
    public void delete(long uid, long chatId) {

    }

    @Override
    public void recall(long uid, long targetId, long mid) {

    }

    private String getChatMainKey(long uid, long targetId) {
        String token;

        if (uid > targetId) {
            token = uid + ":" + targetId;
        } else {
            token = targetId + ":" + uid;
        }
        return token;
    }
}
