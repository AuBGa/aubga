package com.aubga.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserExternalDemo {
	public static void main(String[] args) throws IOException {
		 	UserExternal user = new UserExternal();
	        user.setName("hollis");
	        user.setAge(23);
	        System.out.println(user);
	        
	       ObjectOutputStream oos = null ;
	       
	       try {
	    	oos = new ObjectOutputStream(new FileOutputStream("tempfile"));
	    	oos.writeObject(user);
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }finally {
			if(null != oos) {
				oos.close();
			}
			
			
			File file = new File("tempfile");
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				UserExternal user1 = (UserExternal)ois.readObject();
				System.out.println(user1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	    
	}
}