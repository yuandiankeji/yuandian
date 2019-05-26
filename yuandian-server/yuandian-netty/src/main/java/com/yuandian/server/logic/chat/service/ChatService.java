package com.yuandian.server.logic.chat.service;

import com.yuandian.server.logic.chat.entity.ChatPo;

import java.util.List;

public interface ChatService {

    public void saveChat(ChatPo chatPo);

    public List<ChatPo> getChatInfo(long uid, long targetId, int limit);

    public void delete(long uid, long chatId);

    public void recall(long uid,long targetId,long mid);
    public String test();



}
