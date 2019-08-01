package com.yuandian.core.common;

public abstract class RedisCache<T> {

    public abstract String serialize();

    public abstract T deserialize(String json);

    public abstract String uniqueKey();

}
