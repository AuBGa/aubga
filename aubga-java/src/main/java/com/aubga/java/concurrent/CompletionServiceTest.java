package com.aubga.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService excutor = Executors.newFixedThreadPool(3);

        //创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(excutor);

        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        //向电商1询价
      //  cs.submit(()->getPriceByS1());
        futureList.add(cs.submit(()->getPriceByS1()));

        //向电商2询价
      //  cs.submit(()->getPriceByS2());
        futureList.add(cs.submit(()->getPriceByS2()));
        //向电商3询价
     //   cs.submit(()->getPriceByS3());
        futureList.add(cs.submit(()->getPriceByS3()));

        for(int i=0;i<3;i++) {
            Integer r = cs.take().get();
            excutor.execute(()->save(r));
        }

        Integer result = 0;
        try {

            for(int i=0;i<3;i++) {
                result = cs.take().get();
                if(null != result) {
                    break;
                }
            }

        }finally {
            for(Future<Integer> future:futureList) {
                future.cancel(true);
            }
        }
    }

    private static void save(Integer r) {
        System.out.println("保存价格...");
    }

    private static Integer getPriceByS3() {
        return 55;
    }

    private static Integer getPriceByS2() {
        return 44;
    }

    private static Integer getPriceByS1() {
        return 33;
    }

}
