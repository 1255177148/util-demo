package com.example.demo.entity;

/**
 * @Author hezhan
 * @Date 2019/10/23 14:31
 * 用于测试原型设计模式（作为引用对象）
 */
public class PrototypeSon implements Cloneable {
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
}
