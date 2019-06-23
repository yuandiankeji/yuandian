package com.yuandian.server.logic.friends.handler;

import com.yuandian.data.common.PApplyInfo;
import com.yuandian.data.common.PApplyInfos;
import com.yuandian.entity.ApplyPo;
import com.yuandian.server.core.annotation.MessageAnnotation;
import com.yuandian.server.core.factory.SpringBeanFactory;
import com.yuandian.server.core.net.IoClient;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.core.common.MessageCmd;
import com.yuandian.server.logic.AbstractTcpHandler;
import com.yuandian.server.logic.model.UserInfo;

import java.util.List;

/**
 *申请列表
 */
@MessageAnnotation(cmd = MessageCmd.ALL_FRIEND_APPLY_LIST)
public class AllApplyList extends AbstractTcpHandler {
    @Override
    public void handler(IoClient client, short cmd, byte[] bytes) {
        UserInfo userInfo = IoClientManager.getUserInfo(client);

        long uid = userInfo.getUid();
        List<ApplyPo> list = SpringBeanFactory.getInstance().getFriendService().getApplyList(uid);
        PApplyInfos.Builder pApplyInfos = PApplyInfos.newBuilder();
        for (ApplyPo applyPo : list) {
            long ctime = applyPo.getcTime();
            long targetId = applyPo.getTargetId();
            PApplyInfo.Builder pApplyInfo = PApplyInfo.newBuilder();
            pApplyInfo.setApplyTime(ctime);
            pApplyInfo.setTargetUid(targetId);
            pApplyInfos.addApplyList(pApplyInfo);
        }
        userInfo.writeData(cmd, pApplyInfos.build().toByteArray());
    }
}
