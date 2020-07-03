package com.aubga.java.proxy;

public class StaticProxyDemo {
	public static void main(String[] args) {
		Vendor vendor = new Vendor();
		Sell proxy = new StaticProxy(vendor);
		proxy.sell();
		proxy.ad();
	}
}
