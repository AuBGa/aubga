package com.aubga.redis;

import java.util.Date;

import redis.clients.jedis.Jedis;

public class Test {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.103.27.16", 6379);
		// 2. jedis执行set操作
		jedis.set("hello-3", "world-3");
		//3. jedis执行get操作, value="world"
		String value = jedis.get("hello-3");
		System.out.println(value);
		
		ProtostuffSerializer  protostuffSerializer = new ProtostuffSerializer();
		String key = "club:1";
		// 定义实体对象
		Club club = new Club(1, "AC", "米兰", new Date(), 1);
		// 序列化
		byte[] clubBtyes = protostuffSerializer.serialize(club);
		jedis.set(key.getBytes(), clubBtyes);
		
		byte[] resultBtyes = jedis.get(key.getBytes());
		// 反序列化[id=1, clubName=AC, clubInfo=米兰, createDate=Tue Sep 15 09:53:18 CST
		// 2015, rank=1]
		Club resultClub = protostuffSerializer.deserialize(resultBtyes);
		
		System.out.println(resultClub);
	}
}
