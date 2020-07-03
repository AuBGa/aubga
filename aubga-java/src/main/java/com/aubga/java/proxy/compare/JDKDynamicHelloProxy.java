package com.aubga.java.proxy.compare;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicHelloProxy implements InvocationHandler {
    private Object proxied = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello proxy");
        return method.invoke(proxied, args);
    }
    
    public static void main(String[] args) {
        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, new JDKDynamicHelloProxy());

        hello.hi("world");
    }
}