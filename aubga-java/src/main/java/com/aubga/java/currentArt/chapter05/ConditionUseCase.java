package com.aubga.java.currentArt.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10-20
 */
public class ConditionUseCase {
    Lock      lock      = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition empty = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
            empty.await();
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
