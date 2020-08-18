package com.aubga.redis.distributeLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Properties;

public class RedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);

    private JedisPool pool;

    private static RedisClient instance = new RedisClient();

    private RedisClient() {
        init();
    }

    public static RedisClient getInstance(){
        return instance;
    }

    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = getJedis();
        Object result = jedis.eval(script, keys, args);
        jedis.close();
        return result;
    }

    public String get(final String key){
        Jedis jedis = getJedis();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public String set(final String key, final String value, final String nxxx, final String expx, final int time) {

        SetParams setParams = new SetParams().nx().px(10000);

        Jedis jedis = getJedis();
        String result = jedis.set(key, value, setParams);
        jedis.close();
        return result;
    }

    private void init(){
        Properties redisConfig = PropsUtil.loadProps("redis.properties");
        int maxTotal = PropsUtil.getInt(redisConfig,"maxTotal",10);
        String ip = PropsUtil.getString(redisConfig,"ip","127.0.0.1");
        int port = PropsUtil.getInt(redisConfig,"port",6379);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        pool = new JedisPool(jedisPoolConfig, ip,port);
        //LOGGER.info("连接池初始化成功 ip={}, port={}, maxTotal={}",ip,port,maxTotal);
    }

    private Jedis getJedis(){
        return pool.getResource();
    }
}