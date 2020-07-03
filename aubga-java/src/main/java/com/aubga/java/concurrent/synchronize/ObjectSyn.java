package com.aubga.java.concurrent.synchronize;

import java.util.concurrent.atomic.AtomicInteger;

public class ObjectSyn {

    public static void main(String[] args) {
        //一个实例对象
        ObjectSyn obj = new ObjectSyn();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj.method1(); //执行obj对象的method1方法
            }
        },"t1");


        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj.method2(); //执行obj对象的method2方法
            }
        },"t2");


        t1.start();
        t2.start();
    }

    //方法一
    public synchronized void method1(){
        AtomicInteger i = new AtomicInteger(0);
        while(true){
            i.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " Integer is " + i.get());
            if(i.get() == 100) {
                break;
            }

        }

    }

    //方法二
    public synchronized void method2(){
        AtomicInteger i = new AtomicInteger(0);
        while(true){
            i.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " Integer is " + i.get());
            if(i.get() == 100) {
                break;
            }

        }
    }

}

