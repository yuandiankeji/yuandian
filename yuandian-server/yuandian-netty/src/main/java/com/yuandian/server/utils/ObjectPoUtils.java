package com.yuandian.server.utils;

import com.yuandian.core.common.RedisKeyUtils;
import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.core.utils.ZStringUtil;
import com.yuandian.data.common.PChatUserListInfo;
import com.yuandian.data.common.PChatUserListInfos;
import com.yuandian.data.common.PUserBaseInfo;
import com.yuandian.data.common.PUserBaseInfos;
import com.yuandian.server.config.RedisService;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.logic.chat.service.ChatService;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;

import java.util.List;

/**
 * 对象转化工具类
 */
public class ObjectPoUtils {

    public static PUserBaseInfos getPuserBaseInfos(List<UserPo> userPOList) {
        PUserBaseInfos.Builder pUserBaseInfos = PUserBaseInfos.newBuilder();
        userPOList.forEach((userPO) -> {
            PUserBaseInfo builder = getPUserBaseInfo(userPO);
            pUserBaseInfos.addBaseInfos(builder);
        });
        return pUserBaseInfos.build();

    }

    public static PUserBaseInfo getPUserBaseInfo(UserPo userPO) {
        PUserBaseInfo.Builder builder = PUserBaseInfo.newBuilder();
        builder.setAccount(userPO.getAccount());
        builder.setHeadUrl(userPO.getHeadUrl());
        builder.setNickName(userPO.getNickName());
        builder.setPhoneNum(userPO.getPhoneNum());
        builder.setUid(userPO.getUid());
        builder.setSex(userPO.getSex() + "");
        return builder.build();
    }

    /**
     * 封装聊天列表
     *
     * @param uid
     * @param userPOList
     * @return
     */
    public static PChatUserListInfos getPChatUserListInfos(long uid, List<UserPo> userPOList) {
        PChatUserListInfos.Builder infos = PChatUserListInfos.newBuilder();
        RedisService redisChatService = SpringBeanFactory.getInstance().getRedisChatService();
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        userPOList.forEach((userPO) -> {
            String notReadNumStr = redisChatService.getString(RedisKeyUtils.getNotReadChatNum(uid, userPO.getUid()));
            int num = 0;
            if (ZStringUtil.isEmptyStr(notReadNumStr)) {
                num = Integer.parseInt(notReadNumStr);
            }
            PChatUserListInfo.Builder pchat = PChatUserListInfo.newBuilder();
            PUserBaseInfo userBaseInfo = getPUserBaseInfo(userPO);
            pchat.setUserInfo(userBaseInfo);
            ChatPo chatPo = chatService.getLastChatInfo(uid, userPO.getUid());
            String message = "";
            long cTime = 0;
            if (chatPo != null) {
                message = chatPo.getContext();
                cTime = chatPo.getCtime();
            }
            pchat.setNoReadNum(num);
            pchat.setMessage(message);
            pchat.setTime(cTime);
            pchat.setUid(uid);
            infos.addList(pchat);

        });
        return infos.build();

    }
}
