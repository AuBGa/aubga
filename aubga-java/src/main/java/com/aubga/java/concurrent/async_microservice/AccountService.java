package com.aubga.java.concurrent.async_microservice;

import java.util.concurrent.CompletableFuture;

public interface AccountService {
    /**
     * 变更账户金额
     * @param account
     * @param amount
     * @return
     */
    CompletableFuture<Void> add(int account,int amount);
}
