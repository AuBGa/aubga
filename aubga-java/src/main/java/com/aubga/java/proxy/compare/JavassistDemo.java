package com.aubga.java.proxy.compare;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistDemo {

    public void operation(String value) {
        System.out.println("operation... " + value);
    }

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.aubga.java.proxy.compare.SubJavassistDemo");
        ctClass.setSuperclass(classPool.get("com.aubga.java.proxy.compare.JavassistDemo"));

        // 调用方法
        ((JavassistDemo) ctClass.toClass().newInstance()).operation("");

        // 添加方法并调用
        ctClass = classPool.makeClass("com.aubga.java.proxy.compare.SubJavassistDemo2");
        ctClass.setSuperclass(classPool.get("com.aubga.java.proxy.compare.JavassistDemo"));
        ctClass.addMethod(CtNewMethod.make(
                "public void operation(String value) { System.out.println(\"operation... from Sub\"); }", ctClass));
        ((JavassistDemo) ctClass.toClass().newInstance()).operation("");

        // 更改现有方法
        ctClass = classPool.get("com.aubga.java.proxy.compare.JavassistDemo");
        CtMethod method = ctClass.getDeclaredMethod("operation");
        method.insertBefore("{ System.out.println($1); }"); // $1 表示函数入栈第一个参数
        new JavassistDemo().operation("param1");
    }
}