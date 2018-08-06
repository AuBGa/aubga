package com.aubga.serializable;

import java.io.Serializable;

/**
 * java序列化和反序列化需要事先Serializable接口
 * @author aubga
 *
 */
public class User implements Serializable {
	 
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
    
}