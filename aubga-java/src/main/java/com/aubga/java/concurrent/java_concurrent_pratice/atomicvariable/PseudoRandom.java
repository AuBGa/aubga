package com.aubga.java.concurrent.java_concurrent_pratice.atomicvariable;

public class PseudoRandom {
    private ThreadLocal<Integer> lastSeed = new ThreadLocal<Integer>();

    public int calculateNext(int seed) {
        lastSeed.set(seed);
        return lastSeed.get() + 0;
    }
}
