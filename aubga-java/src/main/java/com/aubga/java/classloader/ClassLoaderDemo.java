package com.aubga.java.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import jdk.management.resource.internal.inst.SocketInputStreamRMHooks;
import sun.net.spi.nameservice.dns.DNSNameService;

public class ClassLoaderDemo {
    public static void main(String[] args) throws IOException {
       /* System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("DNSNameService's ClassLoader is " + DNSNameService.class.getClassLoader());
        System.out.println("String's ClassLoader is " + String.class.getClassLoader());*/
    	
    	
    	System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
        

    }
}