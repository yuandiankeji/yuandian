package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.DateConstants;
import com.yuandian.core.common.RedisKeyUtils;
import com.yuandian.core.common.Rediskey;
import com.yuandian.server.config.RedisService;
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
    @Autowired
    RedisService redisChatService;


    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public void saveChat(ChatPo chatPo, boolean online) {
        String key = RedisKeyUtils.getChatInfoListKey(chatPo.getUid(), chatPo.getTargetId());
        redisChatService.zAdd(key, chatPo.serialize(), chatPo.getMid());
        String user_list_key = String.format(Rediskey.CHAT_USER_LIST, chatPo.getUid());
        redisChatService.saddString(user_list_key, chatPo.getTargetId() + "");
        if (!online) {
            String incrKey = RedisKeyUtils.getNotReadChatNum(chatPo.getTargetId(), chatPo.getUid());
            redisChatService.incr(incrKey);
        }

    }

    @Override
    public List<ChatPo> getChatInfo(long uid, long targetId, int limit) {
        String key = RedisKeyUtils.getChatInfoListKey(uid, targetId);
        Set<String> data = redisChatService.zrangeByScore(key, 0, limit, limit);
        List<ChatPo> list = new ArrayList<>();
        for (String e : data) {
            ChatPo po = new ChatPo();
            po = (ChatPo) po.deserialize(e);
            list.add(po);
        }
        return list;
    }

    @Override
    public void delete(long uid, long targetId, long mid) {
        String key = RedisKeyUtils.getChatInfoListKey(uid, targetId);
        redisChatService.hdel(key, mid + "");
    }

    @Override
    public long read(long uid, long targetId) {
        //this.getChatInfo(uid,targetId);
        return 0;
    }

    /**
     * 聊天好友
     */
    @Override
    public List<UserPo> getChatUserInfo(long uid) {
        List<UserPo> userPOList = new ArrayList<>();
        String key = String.format(Rediskey.CHAT_USER_LIST, uid);
        logger.info(key);
        Set<String> allChatUserId = redisChatService.smembersString(key);
        logger.info("chat user size=" + allChatUserId.size());
        for (String targetUid : allChatUserId) {
            long target_uid = Long.parseLong(targetUid);
            UserPo friend = userService.getUserInfo(target_uid);
            userPOList.add(friend);
        }
        return userPOList;
    }


}
