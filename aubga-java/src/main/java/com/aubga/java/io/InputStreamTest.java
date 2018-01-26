package com.aubga.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputStreamTest {
	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("d:\\allinone\\SyncSchema.json");
			
			System.out.println("user.dir");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
