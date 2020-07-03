package com.aubga.java.concurrent.synchronize;

import java.util.concurrent.TimeUnit;

public class ThreadJoinTest {
    public static void main(String[] args) {

        Thread joinThread = new Thread(new JoinRunner());
        joinThread.start();

        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("join is over");
    }

  static  class JoinRunner implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("begin to execute sleep 3 seconds...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("end to execute sleep 3 seconds...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
