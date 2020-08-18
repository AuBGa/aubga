package com.aubga.java.concurrent.java_concurrent_pratice.threadsafe;

import com.aubga.java.concurrent.java_concurrent_pratice.annotations.NotThreadSafe;

/**
 * LazyInitRace
 *
 * Race condition in lazy initialization
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}

class ExpensiveObject { }

