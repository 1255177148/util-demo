package com.example.demo.entity;

import lombok.Data;

/**
 * <p>Java 参数传递方式测试</p>
 * <p>Java 是值传递，没有引用传递</p>
 *
 * @Author Zhanzhan
 * @Date 2022/2/18 10:52
 **/
@Data
public class TransferTest {

    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;
        swap(num1, num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        /*
        通过上面的例子我们发现，Java是值传递，但是如果入参是引用的对象呢，
        看下面的例子
         */
        User user = new User();
        user.setName("呵呵");
        System.out.println("传递前user的属性为：" + user.getName());
        changeObject(user);
        System.out.println("传递后user的属性为：" + user.getName());
        /*
        通过上面的例子我们会容易形成一个误解，
        就是入参是对象的话，看起来显示引用传递，而不是值传递，
        其实这里还是值传递，只不过传递的值 是对象的引用，也就是对象的指针，
        具体可以看下面的例子，来反驳 对象时引用传递
         */
        User u1 = new User();
        u1.setName("张三");
        User u2 = new User();
        u2.setName("李四");
        swapObject(u1, u2);
        System.out.println("用户1的属性为：" + u1.getName());
        System.out.println("用户2的属性为：" + u2.getName());
    }

    /**
     * 测试传递类型
     *
     * @param a
     * @param b
     */
    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    /**
     * 测试Java入参是对象时的传递类型，依然是值传递，但是这里的值 是此对象的引用
     *
     * @param user
     */
    private static void changeObject(User user) {
        user.setName("变换一下");
    }

    private static void swapObject(User u1, User u2) {
        User temp = u1;
        u1 = u2;
        u2 = temp;
        System.out.println("u1的属性为：" + u1.getName());
        System.out.println("u2的属性为：" + u2.getName());
    }
}
