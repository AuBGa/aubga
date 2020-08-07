package com.aubga.redis.redisweb;

import redis.clients.jedis.Jedis;

public class JedisTest
{
    public static void main(String[] args)
    {
        /*
          连接到linux下的redis  host：机器名, port：redis端口号  如果端口是6379可以不显示写上
          如果要使用win 连接 linux上的redis服务器 需要做的三件事情
          1.关闭防火墙
          2.在redis中 将bind 127.0.0.1注释掉 ，解决redis只容许本地连接
          3.在redis中 将protected-mode yes 改为no
          4.开启redis服务
         */
        Jedis jedis = new Jedis("192.168.2.171",6379);

        //  查看是否连接成功
        System.out.println(jedis.ping());
    }

}
