package com.yuandian.server.logic;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.data.common.PUserInfo;

import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.MessageCmd;

/**
 * @author twjitm 2019/4/6/21:05
 */
@MessageAnnotation(cmd = MessageCmd.GET_USER_INFO)
public class GetUserUserInfo extends AbstractTcpHandler {

    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {


    }
}
