package com.aubga.java.proxy;

public class Vendor implements Sell {

	@Override
	public void sell() {
		System.out.println("sell methond");
	}

	@Override
	public void ad() {
		System.out.println("ad methond");
	}

}
