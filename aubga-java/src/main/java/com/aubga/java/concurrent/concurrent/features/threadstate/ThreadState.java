package com.aubga.java.concurrent.concurrent.features.threadstate;

import com.aubga.java.concurrent.concurrent.util.ThreadDumpHelper;
import com.aubga.java.concurrent.concurrent.util.ThreadUtil;

/**
 * 描述:
 * 线程状态一览
 *
 * @author zed
 */
public class ThreadState {
    private static ThreadDumpHelper threadDumpHelper = new ThreadDumpHelper();

    public static void main(String[] args) {

        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting").start();
        new Thread(new Blocked(),"Blocked1").start();
        new Thread(new Blocked(),"Blocked2").start();

        threadDumpHelper.tryThreadDump();

    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                ThreadUtil.sleep(1000);
            }
        }
    }

    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Blocked.class){
                    ThreadUtil.sleep(1000);
                }
            }
        }
    }
}

