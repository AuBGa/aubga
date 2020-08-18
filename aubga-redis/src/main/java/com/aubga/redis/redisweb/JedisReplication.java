package com.aubga.redis.redisweb;

import com.aubga.redis.IP_AND_PORT;
import redis.clients.jedis.Jedis;

//  主从复制类
public class JedisReplication
{
    public static void main(String[] args)
    {
        //  主机
        Jedis jedis_m = new Jedis(IP_AND_PORT.IP,IP_AND_PORT.PORT);
        //  从机
        Jedis jedis_s = new Jedis(IP_AND_PORT.SLAVE_IP,IP_AND_PORT.SLAVE_PORT);

        //  创建二者关系
        jedis_s.slaveof(IP_AND_PORT.IP,IP_AND_PORT.PORT);

        //  主机写
        jedis_m.set("class","6379");

        //  从机读
        String aClass = jedis_s.get("class");
        System.out.println(aClass);


    }
}
