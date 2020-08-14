package com.aubga.java.currentArt.chapter01;

public class SynchronizedDemo {

    /**
     *  同步方法
     */
    public synchronized void testSynchronizedMethod() {
        System.out.println("test synchronized method");
    }

    /**
     * 同步静态方法
     */
    public synchronized static void testSynchronizedStaticMethod() {
        System.out.println("test synchronized static method");
    }

    /**
     * 同步方法块
     */
    public void testSynchronizedMethodBlock() {
        synchronized (this) {
            System.out.println("test synchronized method block");
        }
    }
}
