package com.example.demo.entity;

import com.example.demo.exception.RemoteException;

/**
 * @Author hezhan
 * @Date 2019/10/23 14:30
 * 用于测试原型设计模式
 */
public class Prototype implements Cloneable {
    private String name;
    private int age;
    private PrototypeSon prototypeSon;

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

    public PrototypeSon getPrototypeSon() {
        return prototypeSon;
    }

    public void setPrototypeSon(PrototypeSon prototypeSon) {
        this.prototypeSon = prototypeSon;
    }

    public Prototype clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RemoteException("克隆Prototype对象实例时出错");
        }
        return prototype;
    }
}
