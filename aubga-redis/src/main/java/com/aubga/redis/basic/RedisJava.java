package com.aubga.redis.basic;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.*;

public class RedisJava {
    public static void main(String[] args) {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("10.103.27.16",6380);
        System.out.println("connect to redis server success...");
        // 查看服务是否运行
        System.out.println("server is running: " + jedis.ping());


        //设置字符串
        jedis.set("souvc", "this is string test");
        // 获取存储的数据并输出
        System.out.println("redis存储的字符串是: " + jedis.get("souvc"));


        jedis.del("classes");
        //列表实例1
        // 存储数据到列表中
        jedis.lpush("classes", "java");
        jedis.lpush("classes", "php");
        jedis.lpush("classes", "Mysql");
        jedis.rpush("classes","golang");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange("classes", 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("redis list里面存储的值是:" + list.get(i)+",i:"+i);
        }

        //列表存储实例2
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));

        //先向key java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");

        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");

       // jedis.sort("java framework");

        System.out.println(jedis.lrange("java framework",0,-1));

        //列表存储实例3
        String idx_0 = jedis.lindex("java framework",0);
        System.out.println("idx_0:"+idx_0);

        jedis.linsert("java framework",ListPosition.BEFORE,"spring","Julia");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.llen("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.lpop("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.rpop("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));


        //Map存储

        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");


        //jedis.hmset("user",map);
        jedis.hset("user",map);
        jedis.hmset("user_2",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }

        //测试set集合
        jedis.sadd("users","role_a");
        jedis.sadd("users","role_b");
        jedis.sadd("users","role_c");
        jedis.sadd("users","role_d");
        jedis.sadd("users","who");
        jedis.sadd("users","role_d");

        //移除noname
        jedis.srem("users","who");
        System.out.println(jedis.smembers("users"));//获取所有加入的value
        System.out.println(jedis.sismember("users", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("users"));
        System.out.println(jedis.scard("users"));//返回集合的元素个数


        jedis.sadd("class_a","aaaa");
        jedis.sadd("class_a","bbbb");
        jedis.sadd("class_a","cccc");

        jedis.sadd("class_b","aaaa");
        jedis.sadd("class_b","dddd");
        jedis.sadd("class_b","ffff");

        Set<String> diffSet = jedis.sdiff("class_a","class_b");
        System.out.println("diffSet:"+diffSet);

        Set<String> unionSet = jedis.sunion("class_a","class_b");
        System.out.println("unionSet:"+unionSet);

        Set<String> interSet = jedis.sinter("class_a","class_b");
        System.out.println("interSet:"+interSet);



        //集合的交集、并集、差集
        for(int i=0;i<100;i++) {
            jedis.sadd("chou_jiang",i+"");

        }

        for(int i=0;i<100;i++) {
            String random = jedis.srandmember("chou_jiang");
            System.out.println("random:"+random);
            jedis.srem("chou_jiang",random);
            System.out.println(jedis.scard("chou_jiang"));
        }
    }
}