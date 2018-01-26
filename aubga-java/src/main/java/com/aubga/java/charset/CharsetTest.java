package com.aubga.java.charset;

import java.nio.charset.Charset;
import java.util.SortedMap;

import org.junit.Test;

public class CharsetTest {

	@Test
	public void test() {
		SortedMap<String,Charset> charsets = Charset.availableCharsets();
		for(String name : charsets.keySet()) {
			System.out.println(name);
		}
	}

}
