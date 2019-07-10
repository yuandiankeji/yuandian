package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.DateConstants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.server.config.RedisFactory;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.logic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserService userService;


    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public void saveChat(ChatPo chatPo) {
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_MESSAGE_INFO_LIST, getChatMainKey(chatPo.getUid(), chatPo.getTargetId()));
        chatRedis.hsetString(key, chatPo.getMid() + "", chatPo.serialize(), DateConstants.SECOND);
        String user_list_key = String.format(Rediskey.CHAT_USER_LIST, chatPo.getUid());
        chatRedis.saddString(user_list_key, chatPo.getTargetId() + "");

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

    /**
     * 聊天好友
     */
    @Override
    public List<UserPo> getChatUserInfo(long uid) {
        List<UserPo> userPOList = new ArrayList<>();
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_USER_LIST, uid);
        logger.info(key);
        Set<String> allChatUserId = chatRedis.smembersString(key);
        logger.info("chat user size="+allChatUserId.size());
        for (String targetUid : allChatUserId) {
            long target_uid = Long.parseLong(targetUid);
            UserPo friend = userService.getUserInfo(target_uid);
            userPOList.add(friend);
        }
        return userPOList;
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
