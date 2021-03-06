package com.aubga.redis.distributeLock;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class RedisDistributeLock implements DistributeLock {

    /**
     * 无限重试
     * */
    public static final int UN_LIMIT_RETRY = -1;

    private RedisDistributeLock() {
        LuaScript.init();
    }

    private static DistributeLock instance = new RedisDistributeLock();

    /**
     * 持有锁 成功标识
     * */
    private static final Long ADD_LOCK_SUCCESS = 1L;
    /**
     * 释放锁 失败标识
     * */
    private static final Integer RELEASE_LOCK_SUCCESS = 1;

    /**
     * 默认过期时间 单位：秒
     * */
    private static final int DEFAULT_EXPIRE_TIME_SECOND = 300;
    /**
     * 默认加锁重试时间 单位：毫秒
     * */
    private static final int DEFAULT_RETRY_FIXED_TIME = 3000;
    /**
     * 默认的加锁浮动时间区间 单位：毫秒
     * */
    private static final int DEFAULT_RETRY_TIME_RANGE = 1000;
    /**
     * 默认的加锁重试次数
     * */
    private static final int DEFAULT_RETRY_COUNT = 30;

    /**
     * lockCount Key前缀
     * */
    private static final String LOCK_COUNT_KEY_PREFIX = "lock_count:";

    public static DistributeLock getInstance(){
        return instance;
    }

    @Override
    public String lock(String lockKey) {
        String uuid = UUID.randomUUID().toString();

        return lock(lockKey,uuid);
    }

    @Override
    public String lock(String lockKey, int expireTime) {
        String uuid = UUID.randomUUID().toString();

        return lock(lockKey,uuid,expireTime);
    }

    @Override
    public String lock(String lockKey, String requestID) {
        return lock(lockKey,requestID,DEFAULT_EXPIRE_TIME_SECOND);
    }

    @Override
    public String lock(String lockKey, String requestID, int expireTime) {
        RedisClient redisClient = RedisClient.getInstance();

        List<String> keyList = Arrays.asList(
                lockKey
        );

        List<String> argsList = Arrays.asList(
                requestID,
                expireTime + ""
        );
        Long result = (Long)redisClient.eval(LuaScript.LOCK_SCRIPT, keyList, argsList);

        if(result.equals(ADD_LOCK_SUCCESS)){
            return requestID;
        }else{
            return null;
        }
    }

    @Override
    public String lockAndRetry(String lockKey) {
        String uuid = UUID.randomUUID().toString();

        return lockAndRetry(lockKey,uuid);
    }

    @Override
    public String lockAndRetry(String lockKey, String requestID) {
        return lockAndRetry(lockKey,requestID,DEFAULT_EXPIRE_TIME_SECOND);
    }

    @Override
    public String lockAndRetry(String lockKey, int expireTime) {
        String uuid = UUID.randomUUID().toString();

        return lockAndRetry(lockKey,uuid,expireTime);
    }

    @Override
    public String lockAndRetry(String lockKey, int expireTime, int retryCount) {
        String uuid = UUID.randomUUID().toString();

        return lockAndRetry(lockKey,uuid,expireTime,retryCount);
    }

    @Override
    public String lockAndRetry(String lockKey, String requestID, int expireTime) {
        return lockAndRetry(lockKey,requestID,expireTime,DEFAULT_RETRY_COUNT);
    }

    @Override
    public String lockAndRetry(String lockKey, String requestID, int expireTime, int retryCount) {
        if(retryCount <= 0){
            // retryCount小于等于0 无限循环，一直尝试加锁
            while(true){
                String result = lock(lockKey,requestID,expireTime);
                if(result != null){
                    return result;
                }

                // 休眠一会
                sleepSomeTime();
            }
        }else{
            // retryCount大于0 尝试指定次数后，退出
            for(int i=0; i<retryCount; i++){
                String result = lock(lockKey,requestID,expireTime);
                if(result != null){
                    return result;
                }

                // 休眠一会
                sleepSomeTime();
            }

            return null;
        }
    }

    private void sleepSomeTime() {
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean unLock(String lockKey, String requestID) {
        List<String> keyList = Arrays.asList(
                lockKey
        );

        List<String> argsList = Collections.singletonList(requestID);

        Object result = RedisClient.getInstance().eval(LuaScript.UN_LOCK_SCRIPT, keyList, argsList);

        // 释放锁成功
        return RELEASE_LOCK_SUCCESS.equals(result);
    }
}