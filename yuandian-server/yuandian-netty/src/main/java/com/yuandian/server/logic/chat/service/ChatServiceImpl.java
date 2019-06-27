package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.DateConstants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.server.config.RedisFactory;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {


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

    @Override
    public List<UserPo> getChatUserInfo(long uid) {
        //UserPO userPO=userService.selectUserById(uid);
        RedisFactory.Redis chatRedis = RedisFactory.getInstance().getRedis("chat");
        String key = String.format(Rediskey.CHAT_USER_LIST, uid);
        Set<String> allChatUserId = chatRedis.smembersString(key);
        for (String targetUid : allChatUserId) {
            long target_uid = Long.parseLong(targetUid);

        }
        List<UserPo> userPOList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserPo userPO = new UserPo();
            userPO.setAccount("121212");
            userPO.setAge("2424");
            userPO.setHeadUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556968161473&di=d23018d493acb7083a5f6ae49c7a18bb&imgtype=0&src=http%3A%2F%2Fs16.sinaimg.cn%2Fmw690%2F002apk40zy7aHsBI98b4f%26690");
            userPO.setNickName("yuandian");
            userPO.setSex(1);
            userPO.setUid(uid);
            userPO.setStatus(1);
            userPO.setPhoneNum("1008611");
            userPO.setSignature("hello，world");
            userPOList.add(userPO);
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
