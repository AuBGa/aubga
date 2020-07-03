package com.aubga.java.concurrent.async_microservice;

import java.util.concurrent.CompletableFuture;

public class TransferServiceImpl implements TransferService{

    AccountService accountService;
    @Override
    public CompletableFuture<Void> transfer(int fromAcount, int toAmount, int amount) {
        return accountService.add(fromAcount,-1*amount).thenCompose(v->accountService.add(toAmount,amount));
    }
}
