package com.aubga.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonTest {
    public static void main(String[] args) throws  Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.103.27.16:6380");
    //    config.useSingleServer().setPassword("redis1234");

        final RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("lock1");

        try{
            lock.lock();
        }finally{
            lock.unlock();
        }
    }
}
