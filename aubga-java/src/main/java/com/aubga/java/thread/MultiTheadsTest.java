package com.aubga.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiTheadsTest {
	
	//获取数据
	public static List<Integer> getData() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<300000;i++) {
			list.add(i);
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		//
		List<Integer> dataList = getData();
		ExecutorService executorService = Executors.newFixedThreadPool(5);

	}
}
