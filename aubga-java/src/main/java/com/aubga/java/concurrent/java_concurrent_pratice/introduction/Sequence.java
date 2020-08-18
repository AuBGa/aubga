package com.aubga.java.concurrent.java_concurrent_pratice.introduction;

import com.aubga.java.concurrent.java_concurrent_pratice.annotations.GuardedBy;
import com.aubga.java.concurrent.java_concurrent_pratice.annotations.ThreadSafe;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {

    /**
     * 表示使用的锁对象是该对象自身
     */
    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
