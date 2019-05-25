package com.yuandian.core.common;

public interface RedisCache {

    public  String serialize();

    public Object deserialize(String json);
}
