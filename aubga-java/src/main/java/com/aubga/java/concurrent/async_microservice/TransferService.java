package com.aubga.java.concurrent.async_microservice;

import java.util.concurrent.CompletableFuture;

public interface TransferService {

    /**
     * 异步转账服务
     * @param fromAcount
     * @param toAmount
     * @param amount
     * @return
     */
    CompletableFuture<Void> transfer(int fromAcount,int toAmount,int amount);
}
