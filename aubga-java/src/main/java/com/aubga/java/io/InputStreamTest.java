package com.aubga.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class InputStreamTest {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\allinone\\SyncSchema.json"));){
			ois.readObject();
			System.out.println("user.dir");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
