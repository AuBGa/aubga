package com.aubga.redis.distributeLock;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    /**
     * 无限重试
     * */
    int UN_LIMIT_RETRY = RedisDistributeLock.UN_LIMIT_RETRY;

    String lockKey();
    int expireTime();
    int retryCount();
}