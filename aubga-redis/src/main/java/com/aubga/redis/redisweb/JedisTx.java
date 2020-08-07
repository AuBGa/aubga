package com.aubga.redis.redisweb;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

//  测试redis 事务的方法
public class JedisTx
{
    @Test
    public void  jdeisTxTest()
    {
        //  获取redis的连接
        Jedis jedis = new Jedis("192.168.2.171", 6379);

         /*
            redis事务的三步
            1.  开启
            2.  入列
            3.  执行
        */

        //  1.事务的开启  multi开启redis事务块
        Transaction transaction = jedis.multi();

        //  2入列 : 实际上就是进行redis的数据操作 比如set get 值
        transaction.set("tx1","txValue1");
        transaction.set("tx2","txValue22222222");
        transaction.set("tx3","txValue3");

        //  3.执行
        transaction.exec();
        System.out.println(jedis.keys("tx*"));


        //  discard相当于mysql数据库中事务的回滚操作
//        transaction.discard();
//        System.out.println(jedis.get("tx2"));





    }
}
