package com.aubga.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserExternal implements Externalizable{
	 private String name;
	    private int age;
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	    public int getAge() {
	        return age;
	    }
	 
	    public void setAge(int age) {
	        this.age = age;
	    }
	 
	    @Override
	    public String toString() {
	        return "User{" +
	                "name='" + name + '\'' +
	                ", age=" + age +
	                '}';
	    }

		@Override
		public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
			name = (String)input.readObject();
			age = input.readInt();
			
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(name);
			out.writeInt(age);
			
		}
}
