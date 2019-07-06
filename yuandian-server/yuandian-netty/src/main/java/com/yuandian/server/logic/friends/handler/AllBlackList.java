package com.yuandian.server.logic.friends.handler;

import com.yuandian.core.common.MessageCmd;
import com.yuandian.data.common.PBlackListInfo;
import com.yuandian.data.common.PBlackListInfos;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.friends.service.FriendService;
import com.yuandian.server.logic.model.UserInfo;

import java.util.List;

/**
 * 获取黑名单列表
 */
@MessageAnnotation(cmd = MessageCmd.ALL_FRIEND_LIST)
public class AllBlackList extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        FriendService friendService = SpringBeanFactory.getInstance().getFriendService();
        List<Long> list = friendService.getBlacklist(uid);

        PBlackListInfo.Builder pBlackListInfo = PBlackListInfo.newBuilder();
        PBlackListInfos.Builder builder = PBlackListInfos.newBuilder();
        list.forEach(targetId -> {
            pBlackListInfo.setUid(targetId);
            builder.addList(pBlackListInfo);
        });
        userInfo.writeData(cmd, builder.build().toByteArray());

    }
}
