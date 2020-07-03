package com.aubga.java.concurrent.async_microservice;

import java.util.concurrent.ExecutionException;

public class Client {
    private TransferService transferService;

    private final static int A = 1000;
    private final static int B = 1001;

    //同步调用
    public void syncInvoke() throws ExecutionException,InterruptedException {
        transferService.transfer(A,B,100).get();
    }
    //异步调用
    public void asyncInvoke() {
        transferService.transfer(A,B,100).thenRun(()->System.out.println("转账完成"));
    }

}
