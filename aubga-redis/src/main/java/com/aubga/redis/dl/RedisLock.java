package com.aubga.redis.dl;

import com.aubga.redis.IP_AND_PORT;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;


public class RedisLock {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String lock_key = "redis_lock"; //锁键

    protected long internalLockLeaseTime = 3000000;//锁过期时间

    private long timeout = 999999; //获取锁的超时时间


    //SET命令的参数
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);



    JedisPool jedisPool;

    public RedisLock() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(6);
        // 初始化Jedis连接池
        jedisPool = new JedisPool(poolConfig, IP_AND_PORT.IP,IP_AND_PORT.PORT);

    }

    /**
     * 加锁
     * @param id
     * @return
     */
    public boolean lock(String id){
        Jedis jedis = jedisPool.getResource();
        jedis.select(10);
        Long start = System.currentTimeMillis();
        try{
            for(;;){
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lock_key, id, params);
                if("OK".equals(lock)){
                    System.out.println("id:"+id+",成功锁,lock:"+lock);
                    return true;
                }
               // System.out.println("id:"+id+",lock:"+lock);
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l>=timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            jedis.close();
        }
    }


    /**
     * 解锁
     * @param id
     * @return
     */
    public boolean unlock(String id){
        Jedis jedis = jedisPool.getResource();
        String script =
                        "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key), Collections.singletonList(id));
            if("1".equals(result.toString())){
                System.out.println("id:"+id+",成功解锁");
                return true;
            }
           // System.out.println("id:"+id+",失败解锁，result:"+result);
            return false;
        }finally {
            jedis.close();
        }
    }
}