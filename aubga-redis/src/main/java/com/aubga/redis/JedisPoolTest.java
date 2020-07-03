package com.aubga.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

public class JedisPoolTest {
	
	public static void pool() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		// 初始化Jedis连接池
		JedisPool jedisPool = new JedisPool(poolConfig, "10.103.27.16", 6379);
		
		System.out.println(jedisPool.toString());
		
		Jedis jedis = null;
		try {
			// 1. 从连接池获取jedis对象
			jedis = jedisPool.getResource();
			// 2. 执行操作
			String value = jedis.get("hello-3");
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
		// 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
				jedis.close();
			}
		}
		
	}
	
	public static void main(String[] args) {
		mdel();
	}
	
	
	public static void mdel() {
		List<String> keys = new ArrayList<String>();
		keys.add("hello-3");
		Jedis jedis = new Jedis("10.103.27.16",6379);
		
		jedis.getClient().clientList();
		// 1)生成pipeline对象
		Pipeline pipeline = jedis.pipelined();
		// 2)pipeline执行命令，注意此时命令并未真正执行
		for (String key : keys) {
			pipeline.del(key);
		}
		// 3)执行命令
		pipeline.sync();
		System.out.println(jedis.get("hello-3"));
	}
}
