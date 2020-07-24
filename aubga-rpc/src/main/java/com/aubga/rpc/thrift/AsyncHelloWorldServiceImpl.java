package com.aubga.rpc.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

public class AsyncHelloWorldServiceImpl implements HelloWorldService.AsyncIface {

    @Override
    public void say(String username, AsyncMethodCallback<String> resultHandler) throws TException {

    }
}
