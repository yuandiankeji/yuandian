package com.yuandian.server.logic.user.service;

import com.alibaba.fastjson.JSON;
import com.yuandian.core.common.Rediskey;
import com.yuandian.core.utils.ZStringUtil;
import com.yuandian.server.config.RedisService;
import com.yuandian.server.core.net.IoClientManager;
import com.yuandian.server.logic.mapper.UserPoMapper;
import com.yuandian.server.logic.model.UserInfo;
import com.yuandian.server.logic.model.entity.UserPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserPoMapper userPoMapper;
    @Autowired
    RedisService redisChatService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserPo getUserInfo(long uid) {
        String key = Rediskey.USER_INFO;
        String info = null;
        try {
            info = redisChatService.getHgetString(key, uid + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserPo userInfo;
        if (ZStringUtil.isEmptyStr(info)) {
            userInfo = userPoMapper.selectByPrimaryKey(uid);
            String json = JSON.toJSONString(userInfo);
            redisChatService.hsetString(key, uid + "", json,-1);
            logger.info("[save user info into cache]={}", userInfo);

        } else {
            userInfo = JSON.parseObject(info, UserPo.class);
        }
        return userInfo;
    }

    @Override
    public void logout(long uid) {
        UserPo userInfo = this.getUserInfo(uid);
    }
}
