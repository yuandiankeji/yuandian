package com.yuandian.core.aspect;

import com.yuandian.core.annotation.RedisCacheable;
import com.yuandian.core.annotation.RedisInsertable;
import com.yuandian.core.annotation.RedisUpdateable;
import com.yuandian.core.common.Constants;
import com.yuandian.core.enums.RedisDataType;
import com.yuandian.core.utils.RedisService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @author: luyufeng
 * @Date: 2019/4/22
 * aop切面自动缓存
 */

@Component
@Aspect
public class RedisCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Autowired
    private RedisService redisService;

    @Around("@annotation(com.yuandian.core.annotation.RedisCacheable)")
    public Object redisCacheable(final ProceedingJoinPoint jp) throws Throwable {
        Object result;
        Method method = getMethod(jp);
        RedisCacheable cache = method.getAnnotation(RedisCacheable.class);
        final String key = parseKey(cache.key(), cache.field(), method, jp.getArgs());
        RBucket rBucket = redisService.getRBucket(key);
        RedisDataType type = cache.type();
        //缓存过期的话走查DB的逻辑
        if (!rBucket.isExists()) {
            result = jp.proceed();
            switch (type) {
                case STRING:
                    RBucket<Object> str = redisService.getRBucket(key);
                    str.set(result, cache.expire(), cache.timeUnit());
                    break;
                case HASH:
                    RMap rMap = redisService.getRMap(key);
                    rMap.putAll((Map) result);
                    break;
                case LIST:
                    RList rList = redisService.getRList(key);
                    rList.addAll((Collection) result);
                    break;
                case SET:
                    RSet set = redisService.getRSet(key);
                    set.addAll((Collection) result);
                    break;

            }
        } else {
            //未过期则继续查redis
            RExpirable cacheData = null;
            switch (type) {
                case STRING:
                    cacheData = rBucket;
                    break;
                case HASH:
                    cacheData = redisService.getRMap(key);
                    break;
                case LIST:
                    cacheData = redisService.getRList(key);
                    break;
                case SET:
                    cacheData = redisService.getRSet(key);
                    break;
            }
            cacheData.expire(cache.expire(), cache.timeUnit());
            result = cacheData;
        }

        return result;
    }

    @Around("@annotation(com.yuandian.core.annotation.RedisUpdateable)")
    public Object redisUpdateable(final ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        RedisUpdateable cache = method.getAnnotation(RedisUpdateable.class);
        final String key = parseKey(cache.key(), cache.field(), method, jp.getArgs());
        Object result = jp.proceed();
        RedisDataType type = cache.type();
        switch (type) {
            case STRING:
                RBucket rBucket = redisService.getRBucket(key);
                rBucket.set(result, cache.expire(), cache.timeUnit());
                break;
            case HASH:
                RMap rMap = redisService.getRMap(key);
                rMap.putAll((Map) result);
                rMap.expire(cache.expire(), cache.timeUnit());
                break;
            case LIST:
                RList rList = redisService.getRList(key);
                rList.addAll((Collection) result);
                rList.expire(cache.expire(), cache.timeUnit());
                break;
            case SET:
                RSet rSet = redisService.getRSet(key);
                rSet.addAll((Collection) result);
                rSet.expire(cache.expire(), cache.timeUnit());
                break;
        }
        return result;
    }

    @Around("@annotation(com.yuandian.core.annotation.RedisInsertable)")
    public Object redisInsertable(final ProceedingJoinPoint jp) throws Throwable {
        Method method = getMethod(jp);
        RedisInsertable cache = method.getAnnotation(RedisInsertable.class);
        final String key = parseKey(cache.key(), cache.field(), method, jp.getArgs());
        RedisDataType type = cache.type();
        Object result = jp.proceed();

        switch (type) {
            case STRING:
                RBucket rBucket = redisService.getRBucket(key);
                rBucket.set(result, cache.expire(), cache.timeUnit());
                break;
            case HASH:
                RMap rMap = redisService.getRMap(key);
                rMap.putAll((Map) result);
                rMap.expire(cache.expire(), cache.timeUnit());
                break;
            case LIST:
                RList rList = redisService.getRList(key);
                rList.addAll((Collection) result);
                rList.expire(cache.expire(), cache.timeUnit());
                break;
            case SET:
                RSet rSet = redisService.getRSet(key);
                rSet.addAll((Collection) result);
                rSet.expire(cache.expire(), cache.timeUnit());
                break;
        }
        return result;
    }



    public String parseKey(String keyPre, String field, Method method, Object[] args) {
        //SpEL表达式为空默认返回key前缀
        if (StringUtils.isEmpty(field) && StringUtils.isNotEmpty(keyPre)) {
            return keyPre;
        }
        //SpEL表达式为空 key前缀为空默认返回方法名
        if (StringUtils.isEmpty(field) && StringUtils.isEmpty(keyPre)) {
            return method.getName();
        }
        //_号分割
        String SpEL = field.replace("_", "+'_'+");
        //获得被拦截方法参数列表
        LocalVariableTableParameterNameDiscoverer nd = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = nd.getParameterNames(method);
        //使用SpEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SpEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SpEL上下文中
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return keyPre + Constants.hash + parser.parseExpression(SpEL).getValue(context, String.class);
    }

    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;

    }
}
