package com.aubga.java.concurrent.java_concurrent_pratice.explicitlocks;

import java.lang.management.ThreadInfo;

/**
 * @version 2016/11/13.
 */
public interface DeadlockHandler {
    void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}
