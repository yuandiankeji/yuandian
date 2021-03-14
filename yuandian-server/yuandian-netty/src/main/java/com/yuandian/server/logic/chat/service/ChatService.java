package com.yuandian.server.logic.chat.service;

import com.yuandian.core.common.ResultObject;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;

import java.util.List;

public interface ChatService {

    public void saveChat(ChatPo chatPo, boolean online);

    public List<ChatPo> getChatInfo(long uid, long targetId, long minMid, long maxMid, int limit);

    public void delete(long uid, long targetId, long mid);

    public void delete(long uid, long targetId);

    public long read(long uid, long targetId);


    List<UserPo> getChatUserInfo(long uid);

    ChatPo getLastChatInfo(long uid, Long targetId);

    ResultObject<Integer> removeChatUser(long uid, long targetId);
}
