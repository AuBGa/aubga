package com.aubga.java.concurrent.volatileDemo;

public class ReOrderExample {
    int a = 0;
    boolean flag = false;

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
        ReOrderExample reOrderExample = new ReOrderExample();

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
        t2.start();
        t1.start();

    }
}
