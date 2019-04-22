package com.yuandian.core.annotation;

import com.yuandian.core.enums.RedisDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author: luyufeng
 * @Date: 2019/4/22
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisUpdateable {
    String key() default "";
    String field() default "";
    RedisDataType type() default RedisDataType.STRING;
    long expire() default 86400L;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
