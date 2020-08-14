package com.aubga.java.currentArt.chapter04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver_2 {

    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if(method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    static final Connection createConnection() {
        return (Connection)Proxy.newProxyInstance(ConnectionDriver_2.class.getClassLoader(),new Class<?> [] {Connection.class},new ConnectionHandler());
    }
}
