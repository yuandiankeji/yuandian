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
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.entity.ChatPo;
import com.yuandian.server.logic.model.entity.UserPo;

import java.util.List;

/**
 * 对象转化工具类
 */
public class ObjectPoUtils {

    public static PUserBaseInfos getPuserBaseInfos(long uid,List<UserPo> userPos) {
        PUserBaseInfos.Builder pUserBaseInfos = PUserBaseInfos.newBuilder();
        userPos.forEach((userPo) -> {
            PUserBaseInfo builder = getPUserBaseInfo(uid,userPo);
            pUserBaseInfos.addBaseInfos(builder);
        });
        return pUserBaseInfos.build();

    }

    public static PUserBaseInfo getPUserBaseInfo(long uid, UserPo userPo) {
        PUserBaseInfo.Builder builder = PUserBaseInfo.newBuilder();
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        boolean isban = friendService.isban(uid, userPo.getUid());
        builder.setBan(isban);
        boolean isFriend = friendService.isFriend(uid, userPo.getUid());
        builder.setIsFriends(isFriend);
        builder.setAccount(userPo.getAccount());
        builder.setHeadUrl(userPo.getHeadUrl());
        builder.setNickName(userPo.getNickName());
        builder.setPhoneNum(userPo.getPhoneNum());
        builder.setUid(userPo.getUid());
        builder.setSex(userPo.getSex() + "");
        return builder.build();
    }


    /**
     * 封装聊天列表
     *
     * @param uid
     * @param userPos
     * @return
     */
    public static PChatUserListInfos getPChatUserListInfos(long uid, List<UserPo> userPos) {
        PChatUserListInfos.Builder infos = PChatUserListInfos.newBuilder();
        RedisService redisChatService = SpringBeanFactory.getInstance().getRedisChatService();
        ChatService chatService = SpringBeanFactory.getInstance().getChatService();
        userPos.forEach((userPo) -> {
            String notReadNumStr = redisChatService.getString(RedisKeyUtils.getNotReadChatNum(uid, userPo.getUid()));
            int num = 0;
            if (!ZStringUtil.isEmptyStr(notReadNumStr)) {
                num = Integer.parseInt(notReadNumStr);
            }
            PChatUserListInfo.Builder pchat = PChatUserListInfo.newBuilder();
            PUserBaseInfo userBaseInfo = getPUserBaseInfo(uid,userPo);
            pchat.setUserInfo(userBaseInfo);
            ChatPo chatPo = chatService.getLastChatInfo(uid, userPo.getUid());
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
