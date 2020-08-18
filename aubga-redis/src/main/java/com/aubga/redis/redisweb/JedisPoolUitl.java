package com.aubga.redis.redisweb;

import com.aubga.redis.IP_AND_PORT;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//  Jedis线程池
public class JedisPoolUitl
{

    private static volatile JedisPool jedisPool = null;

    private JedisPoolUitl()
    {

    }

    //  连接池业务
    public static JedisPool getJedisPool()
    {
        if (jedisPool == null)
        {
            synchronized (JedisPoolUitl.class)
            {
                //  设置连接池
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(8); //  设置线程池最大的连接个数
                jedisPoolConfig.setMaxIdle(6); //   设置最大空闲数
                jedisPoolConfig.setMaxWaitMillis(1000); //  jedis实例化后最大的等待时间
                jedisPool = new JedisPool(IP_AND_PORT.IP,IP_AND_PORT.PORT);
            }
        }
        return jedisPool;
    }

    //  释放连接
    public static void release(JedisPool jedisPool, Jedis jedis)
    {
        //  获取资源
        jedis =jedisPool.getResource();

        if (jedis != null)
        {
            //  关闭资源
            jedisPool.close();

          //  jedis.close();
        }
    }



}
