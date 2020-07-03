package com.aubga.java.concurrent.volatileDemo;

public class SynchronizedExample {
    int a = 0;
    boolean flag = false;

    public synchronized void writer() {
        a = 1;
        flag = true;
    }

    public synchronized void reader() {

        if(flag) {
            int i = a * a ;
            System.out.print("flag:"+flag+",i:"+i);
        }else {
            System.out.print("flag:"+flag);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample reOrderExample = new SynchronizedExample();

        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        reOrderExample.writer();
                    }
                }
        );
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        reOrderExample.reader();
                    }
                }
        );
        t1.start();
        t2.start();


    }
}
