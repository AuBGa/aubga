package com.aubga.java.proxy;

public class StaticProxy implements Sell {
	
	private Vendor vendor;
	public StaticProxy(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public void sell() {
		vendor.sell();

	}

	@Override
	public void ad() {
		vendor.ad();
	}

}
