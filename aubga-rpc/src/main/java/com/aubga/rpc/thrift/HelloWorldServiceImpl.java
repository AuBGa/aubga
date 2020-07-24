package com.aubga.rpc.thrift;

import org.apache.thrift.TException;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String say(String username) throws TException {
        return "同步:hello";
    }
}
