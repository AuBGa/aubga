package com.aubga.java.lock;

public class Test {

    public static void main(String[] args) throws Exception {
        Lock lock = new ZkSequenTemplateLock();
        lock.getLock();
    }
}
