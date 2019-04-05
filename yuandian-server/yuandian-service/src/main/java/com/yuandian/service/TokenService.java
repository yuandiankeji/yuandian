package com.yuandian.service;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.Constants;
import com.yuandian.core.common.Rediskey;
import com.yuandian.entity.Token;
import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */
@Service
public class TokenService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private IdService idService;

    public Token createToken(long userId, String deviceId) {
        //使用uuid作为源token
        long randomId = idService.genId();
        Token token = new Token(userId, deviceId, randomId);
        //存储到redis并设置过期时间
        RBucket<String> tokenString = redisService.getRBucket(Rediskey.TOKEN + userId);
        tokenString.set(token.getTokenStr(), 1, TimeUnit.DAYS);
        return token;
    }

    public Token getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split(Constants.hash);
        if (param.length != 3) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        return new Token(authentication);
    }

    public boolean checkToken(Token model) {
        if (model == null) {
            return false;
        }
        String token = redisService.getRBucket(Rediskey.TOKEN + model.getUid()).toString();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisService.getRBucket(Rediskey.TOKEN + model.getUid());
        return true;
    }

    public void deleteToken(long userId) {
        redisService.deleteKey(Rediskey.TOKEN + String.valueOf(userId));
    }

}
