package com.aubga.redis.redisweb;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest
{
    //  连接池测试
    @Test
    public void jedisPoolTest(){
        //  redis数据连接池
        JedisPool jedisPool = JedisPoolUitl.getJedisPool();
        Jedis jedis = null;

        try
        {
            //  从池中获取资源
            jedis = jedisPool.getResource();
            //  设置值
            jedis.set("keyPool1","valuePool1");
            //  获取值
            String keyPool1 = jedis.get("keyPool1");
            System.out.println(keyPool1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //  释放资源
            JedisPoolUitl.release(jedisPool,jedis);
        }



    }
}
