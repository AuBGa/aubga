package com.aubga.java.concurrent.volatileDemo;

public class VolatiledExample {

    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {

        if(flag) {
            int i = a * a ;
            System.out.print("flag:"+flag+",i:"+i);
        }else {
            System.out.print("flag:"+flag);
        }
    }

    public static void main(String[] args) {
        VolatiledExample reOrderExample = new VolatiledExample();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        reOrderExample.writer();
                    }
                }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        reOrderExample.reader();
                    }
                }
        ).start();
    }
}
