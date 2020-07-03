package com.aubga.java.proxy.compare;

public class HelloImpl implements Hello {
    @Override
    public void hi(String msg) {
        System.out.println("hello " + msg);
    }
}