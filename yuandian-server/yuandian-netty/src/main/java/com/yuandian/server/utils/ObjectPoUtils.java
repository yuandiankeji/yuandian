package com.yuandian.server.utils;

import com.yuandian.data.common.PUserBaseInfo;
import com.yuandian.data.common.PUserBaseInfos;
import com.yuandian.entity.UserPO;

import java.util.List;

public class ObjectPoUtils {

    public static PUserBaseInfos getPuserBaseInfos(List<UserPO> userPOList) {
        PUserBaseInfos.Builder pUserBaseInfos = PUserBaseInfos.newBuilder();
        userPOList.forEach((userPO) -> {
            PUserBaseInfo.Builder builder = PUserBaseInfo.newBuilder();
            builder.setAccount(userPO.getAccount());
            builder.setHeadUrl(userPO.getHeadUrl());
            builder.setNickName(userPO.getNickName());
            builder.setPhoneNum(userPO.getPhoneNum());
            builder.setUid(userPO.getUid());
            builder.setSex(userPO.getSex() + "");
            pUserBaseInfos.addBaseInfos(builder);

        });
        return pUserBaseInfos.build();

    }
}
