package com.yuandian.server.logic.login;

import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.AbstractTcpHandler;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户下线
 */
@MessageAnnotation(cmd = MessageCmd.LOGOUT)
public class LOGOUT extends AbstractTcpHandler {
    private Logger logger = LoggerFactory.getLogger(LOGOUT.class);

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);
        logger.info("[LOGOUT] | cmd={},data={}", cmd, null);
        UserService userService = SpringBeanFactory.getInstance().getUserService();
        userService.logout(userInfo.getUid());
    }
}
