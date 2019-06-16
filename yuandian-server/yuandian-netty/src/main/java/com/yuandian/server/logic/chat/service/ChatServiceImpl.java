package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.DateConstants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.server.config.RedisFactory;
import com.yuandian.server.logic.chat.entity.ChatPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

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
        logger.info("key=" + key);
        Map<String, String> map = chatRedis.hgetAll(key);
        List<ChatPo> list = new ArrayList<>();
        logger.info("data" +
                +map.size());
        for (Map.Entry<String, String> e : map.entrySet()) {
            ChatPo po = new ChatPo();
            po = (ChatPo) po.deserialize(e.getValue());
            list.add(po);
        }

        logger.info("chat size=" + list.size());
        return list;
    }

    @Override
    public void delete(long uid, long targetId, long mid) {
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_MESSAGE_INFO_LIST, getChatMainKey(uid, targetId));
        chatRedis.hdel(key, mid + "");
    }

    @Override
    public long read(long uid, long targetId) {

        return 0;
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
