package com.aubga.java.currentArt.chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static Lock lock = new ReentrantLock();

    static class Runner implements Runnable{

        boolean flag = false;
        @Override
        public void run() {

            try {
                flag = lock.tryLock(5,TimeUnit.SECONDS);
                if(flag) {
                    TimeUnit.SECONDS.sleep(2);
                    lock.lockInterruptibly();
                }
                System.out.println("lockInterruptibly...."+Thread.currentThread().getName());

            }catch(Exception e) {
                e.printStackTrace();
            }
            finally{
                if(flag) {
                    lock.unlock();
                }else {
                    System.out.println("get lock failed...."+Thread.currentThread().getName());
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i=0;i<1000;i++) {
            Thread t = new Thread(new Runner());
            t.start();
        }
    }
}
