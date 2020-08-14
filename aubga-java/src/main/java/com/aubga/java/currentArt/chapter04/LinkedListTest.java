package com.aubga.java.currentArt.chapter04;

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Student> list = new LinkedList<Student>();
        list.add(new Student("a",1));
        list.add(new Student("b",2));
        list.add(new Student("c",3));
        list.add(new Student("d",4));

        Student student = list.removeFirst();
        System.out.println(student.toString());
    }


}
class Student {
    private String name;
    private int age;

    public Student(){}
    public Student(String name,int age) {
        this.age = age;
        this.name = name;
    }
    public String toString() {
        return "name:"+name+",age:"+age;
    }
}