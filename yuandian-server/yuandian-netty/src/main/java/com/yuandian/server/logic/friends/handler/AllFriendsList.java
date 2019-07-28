package com.yuandian.server.logic.friends.handler;

import com.yuandian.data.common.PUserBaseInfos;

import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.model.entity.FriendPo;
import com.yuandian.server.logic.model.entity.UserPo;
import com.yuandian.server.logic.user.service.UserService;
import com.yuandian.server.utils.ObjectPoUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友列表
 */
@MessageAnnotation(cmd = MessageCmd.ALL_FRIEND_LIST)
public class AllFriendsList extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(AllFriendsList.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        long uid = userInfo.getUid();
        List<Long> friendPoList = SpringBeanFactory.getInstance().getFriendService().getFriendList(uid);
        UserService userService = SpringBeanFactory.getInstance().getUserService();
        List<UserPo> userPOList = new ArrayList<>();

        friendPoList.forEach((friend) -> userPOList.add(userService.getUserInfo(friend)));
        PUserBaseInfos baseInfos = ObjectPoUtils.getPuserBaseInfos(uid,userPOList);
        userInfo.writeData(cmd, baseInfos.toByteArray());
    }
}
