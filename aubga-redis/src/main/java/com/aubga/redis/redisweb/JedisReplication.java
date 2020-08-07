package com.aubga.redis.redisweb;

import redis.clients.jedis.Jedis;

//  主从复制类
public class JedisReplication
{
    public static void main(String[] args)
    {
        //  主机
        Jedis jedis_z = new Jedis("192.168.2.171",6379);
        //  从机
        Jedis jedis_c = new Jedis("192.168.2.171",6380);

        //  创建二者关系
        jedis_c.slaveof("192.168.2.171",6379);

        //  主机写
        jedis_z.set("class","6379");

        //  从机读
        String aClass = jedis_c.get("class");
        System.out.println(aClass);


    }
}
