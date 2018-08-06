package com.aubga.java.thread;

import java.util.concurrent.Callable;

public class TaskSleep implements Callable<Integer> {

    private int num;

    public TaskSleep(int num){
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
//        System.out.println(num + "--->" +i);
        Thread.sleep(1000);
        return  num;
    }
}