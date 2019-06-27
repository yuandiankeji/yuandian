package com.yuandian.server.utils;

import com.yuandian.core.utils.ZDateUtils;
import com.yuandian.data.common.PChatUserListInfo;
import com.yuandian.data.common.PChatUserListInfos;
import com.yuandian.data.common.PUserBaseInfo;
import com.yuandian.data.common.PUserBaseInfos;
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

    public static PChatUserListInfos getPChatUserListInfos(List<UserPo> userPOList) {
        PChatUserListInfos.Builder infos = PChatUserListInfos.newBuilder();
        userPOList.forEach((userPO) -> {
            PChatUserListInfo.Builder pchat = PChatUserListInfo.newBuilder();
            PUserBaseInfo userBaseInfo = getPUserBaseInfo(userPO);
            pchat.setUserInfo(userBaseInfo);
            pchat.setNoReadNum(1);
            pchat.setMessage("你好，世界");
            pchat.setTime(ZDateUtils.getSeconds());
            pchat.setUid(1L);
            infos.addList(pchat);

        });
        return infos.build();

    }
}
