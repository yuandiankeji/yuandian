package com.yuandian.server.logic.chat.service;

import com.alibaba.fastjson.JSON;
import com.yuandian.core.common.*;
import com.yuandian.core.utils.CollectionUtil;
import com.yuandian.server.config.RedisService;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.logic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

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
        redisChatService.zAdd(key, JSON.toJSONString(chatPo), chatPo.getMid());
        String user_list_key = String.format(Rediskey.CHAT_USER_LIST, chatPo.getUid());
        redisChatService.saddString(user_list_key, chatPo.getTargetId());
        String user_list_key_friend = String.format(Rediskey.CHAT_USER_LIST, chatPo.getTargetId());
        redisChatService.saddString(user_list_key_friend, chatPo.getUid());
        String incrKey = RedisKeyUtils.getNotReadChatNum(chatPo.getTargetId(), chatPo.getUid());
        redisChatService.incr(incrKey);

    }

    @Override
    public List<ChatPo> getChatInfo(long uid, long targetId, long minMid, long maxMid, int limit) {
        String key = RedisKeyUtils.getChatInfoListKey(uid, targetId);
        Set<String> data = redisChatService.zrevrangeByScore(key, maxMid, minMid, limit);
        List<ChatPo> list = new ArrayList<>();
        for (String e : data) {
            ChatPo po = JSON.parseObject(e, ChatPo.class);
            if (po.getMid() < maxMid) {
                list.add(po);
            }
        }
        list.sort(Comparator.comparing(ChatPo::getCtime));
        return list;
    }

    @Override
    public void delete(long uid, long targetId, long mid) {
        String key = RedisKeyUtils.getChatInfoListKey(uid, targetId);
        redisChatService.zremRangeByScore(key, mid, mid);

    }

    @Override
    public long read(long uid, long targetId) {
        String key = RedisKeyUtils.getNotReadChatNum(uid, targetId);
        redisChatService.setString(key, "0");
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

    /**
     * 获取最后一条消息
     *
     * @param uid
     * @param targetId
     * @return
     */
    @Override
    public ChatPo getLastChatInfo(long uid, Long targetId) {
        String key = RedisKeyUtils.getChatInfoListKey(uid, targetId);
        Set<ChatPo> lastChatStr = redisChatService.zrange(key, -1, -1, ChatPo.class);
        if (CollectionUtil.isEmpty(lastChatStr)) {
            return null;
        }
        ArrayList<ChatPo> list = new ArrayList<>(lastChatStr);
        return list.get(0);
    }

    /**
     * 移除聊天好友
     *
     * @param uid
     * @param targetId
     * @return
     */
    @Override
    public ResultObject<Integer> removeChatUser(long uid, long targetId) {
        String user_list_key = String.format(Rediskey.CHAT_USER_LIST, uid);
        redisChatService.sremString(user_list_key, targetId + "");
        return new ResultObject<>(ErrorCode.SYS_SUCCESS, 1);
    }


}
