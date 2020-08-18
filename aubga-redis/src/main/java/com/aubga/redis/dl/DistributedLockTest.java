package com.aubga.redis.dl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DistributedLockTest {


    static int count = 0;
    static  Logger logger = LoggerFactory.getLogger(DistributedLockTest.class);

    public static void main(String[] args)  {

      try {
          RedisLock redisLock = new RedisLock();

          int clientcount =1000;
          CountDownLatch countDownLatch = new CountDownLatch(clientcount);


          ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
          long start = System.currentTimeMillis();
          for (int i = 0;i<clientcount;i++){
              executorService.execute(() -> {

                  //通过Snowflake算法获取唯一的ID字符串
                  String id = String.valueOf(UUID.randomUUID());
                  try {
                      redisLock.lock(id);
                      count++;
                  }finally {
                      redisLock.unlock(id);
                      System.out.println("unlock执行...");
                  }
                  countDownLatch.countDown();
              });
            //  System.out.println("循环次数："+i);
          }
          countDownLatch.await();
          long end = System.currentTimeMillis();
          logger.info("执行线程数:{},总耗时:{},count数为:{}");
      }catch (Exception e) {
          e.printStackTrace();
      }
    }
}
