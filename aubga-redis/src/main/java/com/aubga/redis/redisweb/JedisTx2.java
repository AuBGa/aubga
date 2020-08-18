package com.aubga.redis.redisweb;

import com.aubga.redis.IP_AND_PORT;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

//  模拟转账操作
public class JedisTx2
{
    //  转账方法 判断是否成功  成功true 不成功false
    public static   boolean accounts(int b1, int b2, int m){
        //  获取连接
        Jedis jedis = new Jedis(IP_AND_PORT.IP,IP_AND_PORT.PORT);
        //  假如balance1是zs的余额  balance2是李四的余额,现在zs要向ls转账100元
        int balance1 = b1, balance2=b2;
        //  需要转账的钱
        int money = m ;
        String s1 =b1+"";
        String s2 =b2+"";

        //  为了测试能成功，在redis中我并没有创建key为balance1 ，balance2的账号 所有在测试时需要临时创建
        jedis.set("balance1",s1);
        jedis.set("balance2",s2);
        //  给balance2添加监控
        jedis.watch("balance2");
        /*
            模拟一个错误情况 中间加塞 改变了原来的值
        */

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //  在线程失去的控制器的时候 突然有人吧监控的数据该了 ，那么事务是否还会执行  答案是不会
        jedis.set("balance2","100000");


        //  得到添加监控的值
        balance2 = Integer.parseInt(jedis.get("balance2"));
        if(balance2 < money){ // 如果账号上的钱少于需要转的钱那么转账失败
            //  取消所有的监控
            jedis.unwatch();
            System.out.println("事务失败");
            return false;
        }else{//    如果小于证明能转账成功
            //  开启事务
            Transaction transaction = jedis.multi();
            //  进行业务操作 incrBy添加  decrBy减少
            transaction.incrBy("balance1",money);
            transaction.decrBy("balance2",money);
            //  执行事务
            transaction.exec();

            System.out.println("事务成功");
            return true;
        }
    }

    public static void main(String[] args)
    {
        boolean accounts = accounts(100, 100, 50);
        System.out.println(accounts);
    }

}
