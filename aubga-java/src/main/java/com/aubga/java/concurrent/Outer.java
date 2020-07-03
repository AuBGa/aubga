package com.aubga.java.concurrent;

public class Outer {
    //定义一个实例变量和一个静态变量
    private int a;
    private static int b;
    //定义一个静态方法和一个非静态方法
    public static void say(){}
    public void test(){
        //在外部类中调用内部类的属性和方法
        Outer.Inner.c = 1;            //可以通过静态内部类的全类名来调用静态内部类的静态属性（外部类名.静态内部类名.属性）
        Outer.Inner.go();            //可以通过静态内部类的全类名来调用静态内部类的静态方法（外部类名.静态内部类名.方法）
        //Outer.Inner.walk();        //不能通过类静态内部类的全类名来调用内部类的非静态属性和方法
        Inner inner = new Inner();
        inner.d = 1;
        inner.walk();                    //可以通过创建内部类实例来调用静态内部类的非静态属性和方法
    }
    //静态内部类
    public static class Inner{
        //在静态内部类中定义一个静态变量和一个实例变量
        static int c;
        int d;
        String name;
        //定义一个匿名代码块和一个静态代码块
        {}
        static{}
        //定义一个静态方法和一个普通方法
        public static void go(){}
        public void walk(){
            //在静态内部类中调用外部类的属性和方法
            int f = b;                     //可以直接调用外部类的静态属性
            say();                        //可以直接调用外部类的静态方法
            //int e = a;                 直接调用外部类的非静态属性出错编译出错
            //test();                    直接调用外部类的非静态方法时编译出错
            Outer outer = new Outer();
            int e = outer.a;            //可以通过创建外部类实例来调用外部类的非静态属性
            outer.test();                //可以通过创建外部类实例来调用外部类的非静态方法
        }
    }

    public static  void main(String[] args) {
        Outer outer = new Outer();
        outer.test();

        Outer outer1 = new Outer();
        outer1.test();

    }
}