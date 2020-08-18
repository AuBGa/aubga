package com.aubga.redis.redisweb;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// redis指令大全 ： http://redisdoc.com/
public class JedisApi
{
    private static Jedis jedis;

    // 在单元测试启动时先加载该类
    @Before
    public void before()
    {
        // 连接redis
        jedis = new Jedis("10.103.27.16", 6380);
    }

    // jedis 操作 String类型
    @Test
    public void jedisString()
    {
        // 设置值
        jedis.set("key1", "value1");
        jedis.set("key2", "value2");
        jedis.set("key3", "value3");

        // 获取值
        System.out.println(jedis.get("key1"));

        // 获取所有的key
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println(keys.size()); // keys的长度

    }

    // jedis 操作 hash类型
    @Test
    public void jedisMap()
    {
        // 操作hash类型先要创建map类型的集合
        HashMap<String, String> map = new HashMap<>();

        // map类型添加数据
        map.put("name", "zs");
        map.put("sex", "男");
        map.put("age", "13");
        // 将值添加到redis 的hash类型当中
        jedis.hmset("user", map);
        // 取出hash类型当中设置的值  key就是hmset设置的key fields... 传入你前面map集合中添加元素的key 会得到对应的value值
        List<String> hmget = jedis.hmget("user", "user", "sex", "age");
        System.out.println(hmget);
        System.out.println();

        //  删除map中的某个键值
        jedis.hdel("user", "age");
        //  得到单个key
        System.out.println(jedis.hmget("user", "age"));
        //  返回hlen 返回key的长度
        System.out.println(jedis.hlen("user"));
        //  exists 判断是否存在该key
        System.out.println(jedis.exists("user"));
        //  hkeys 返回map对象中的所有key
        System.out.println(jedis.hkeys("user"));
        //  hvals 返回map对象中的所有value
        System.out.println(jedis.hvals("user"));
        System.out.println();

        //  按照格式 打印key value形式
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext())
        {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }

    }

    //  jedis 操作 list类型
    @Test
    public void jedisList()
    {
        //  开始前，先移除所有的内容 以免导致多处测试打印结果混乱
        jedis.del("list");
        jedis.del("list2");
        //  添加数据  list类型 单key 多value  lpush从最左边开始入栈的方式添加 所有会出现倒序排列的方式
        jedis.lpush("list", "v1", "v2", "v3");
        //  取出所有的值  start：开始位置 ，end：结束位置  添加的下标索引从0开始
        System.out.println(jedis.lrange("list", 0, -1));
        //  取出一个值
        System.out.println(jedis.lrange("list", 0, 0));
        System.out.println();

        //  lpush从最右边边开始入栈的方式添加 所有会出现排列的方式
        jedis.rpush("list2", "vv1", "vv2", "vv3");
        System.out.println(jedis.lrange("list2", 0, -1));
    }


    //  jedis操作 set 类型
    @Test
    public void jedisSet()
    {
        //  添加  set也是单key 多value
        jedis.sadd("sets", "s1","s2","s3");
        //  获取所有加入的value
        System.out.println(jedis.smembers("sets"));
        //  移除s1
        jedis.srem("sets", "s1");
        //  判断 s2 是否是user集合的元素
        System.out.println(jedis.sismember("sets", "s2"));
        //  返回集合的元素个数
        System.out.println(jedis.scard("sets"));
    }

    //  jedis操作 zset类型
    @Test
    public void jedisZSet(){
        //  添加值  zset 会根据传入score从小到大的依次排序
        jedis.zadd("zsets", 100, "zs1");
        jedis.zadd("zsets", 80, "zs2");
        jedis.zadd("zsets", 90, "zs3");

        //  得到值
        Set<String> zsets = jedis.zrange("zsets", 0, -1);
        System.out.println(zsets);

        //  删除值 可以删除多个
        jedis.zrem("zsets","zs3","zs1");
        Set<String> zsets2 = jedis.zrange("zsets", 0, -1);
        System.out.println(zsets2);

    }


}
