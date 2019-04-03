package com.yuandian.service;

import com.yuandian.common.Constants;
import com.yuandian.entity.Token;
import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: luyufeng
 * @Date: 2019/3/25
 */
@Service
public class TokenService {
    @Autowired
    private RedisService redisService;

    public Token createToken(long userId, String deviceId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        Token model = new Token(userId, deviceId, token);
        //存储到redis并设置过期时间
        RBucket<String> tokenString = redisService.getRBucket(String.valueOf(userId));
//        tokenString.set();
//        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public Token getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new Token(userId,token, token);
    }

    public boolean checkToken(Token model) {
        if (model == null) {
            return false;
        }
//        String token = redisService.getRBucket("test");
//        if (token == null || !token.equals(model.getToken())) {
//            return false;
//        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
//        redis.boundValueOps(model.getUid()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(long userId) {
//        redisService.
    }

}
