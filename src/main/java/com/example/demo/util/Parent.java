package com.example.demo.util;

public class Parent {

    static int a = 1;
    private static int b;
    private int c = initC();

    static {
        b = 1;
        System.out.println("父类静态代码块：赋值b成功");
        System.out.println("父类静态代码块：a的值为" + a);
    }

    int initC() {
        System.out.println("父类成员变量：c的值为：" + c);
        this.c = 3;
        System.out.println("父类成员变量：c的值为：" + c);
        return c;
    }

    public Parent() {
        System.out.println("父类构造方法开始执行----->a：" + a + "，b：" + b);
        System.out.println("父类构造方法开始执行----->c：" + c);
    }
}
