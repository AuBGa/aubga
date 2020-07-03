package com.aubga.java.concurrent.synchronize;

/**
 * 同步线程
 * syncThread1和syncThread2是SyncThread的两个对象，但在thread1和thread2并发执行时却保持了线程同步。这是因为run中调用了静态方法method，
 * 而静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁。
 */
 class SyncThread_1 implements Runnable {
    private static int count;

    public SyncThread_1() {
        count = 0;
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {
        method();
    }
}

public class Demo04{

    public static void main(String args[]){
        SyncThread_1 syncThread1 = new SyncThread_1();
        SyncThread_1 syncThread2 = new SyncThread_1();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}