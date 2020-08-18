package com.aubga.java.currentArt.chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex_2 implements Lock {

    private static class Sync_2 extends AbstractQueuedSynchronizer {
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态设置为0
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }

    }

    final Sync_2 sync_2 = new Sync_2();

    @Override
    public void lock() {
        sync_2.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync_2.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync_2.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync_2.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync_2.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync_2.newCondition();
    }

    public static void main(String[] args) {
        Mutex_2 m2 = new Mutex_2();
        m2.lock();
        try {
            System.out.println("this is m2 lock");
        }finally{
            m2.unlock();
        }

    }
}
