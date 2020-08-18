package com.aubga.java.concurrent.java_concurrent_pratice.pcpp.week1;

class LongCounter {
    private long count = 0;

    public synchronized void increment() {
        count = count + 1;
    }

    public synchronized long get() {
        return count;
    }
}