package com.example.demo.util;

public class Son extends Parent {

    private static int d = 1;
    private static int e;
    private int f = initF();

    static {
        e = 2;
        System.out.println("子类静态代码块：赋值e成功");
        System.out.println("子类静态代码块：d的值为：" + d);
    }

    int initF() {
        this.f = 3;
        System.out.println("子类成员变量赋值：f=" + f);
        return f;
    }

    public Son() {
        System.out.println("子类构造方法开始加载：d=" + d + "，e=" + e);
        System.out.println("子类构造方法开始加载：f=" + f);
    }
}
