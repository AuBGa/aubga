package com.aubga.java.currentArt.chapter05;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
    Lock rLock = rwLock.readLock();
    Lock wLock = rwLock.writeLock();


    @Test
    public void test() {
        lock.lock();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();//
                try {

                }finally{

                }
            }
        });
    }

}
