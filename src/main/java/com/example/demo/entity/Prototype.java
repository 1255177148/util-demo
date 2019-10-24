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
    private CloneDepth cloneDepth;
    private CloneShallow cloneShallow;

    public CloneShallow getCloneShallow() {
        return cloneShallow;
    }

    public void setCloneShallow(CloneShallow cloneShallow) {
        this.cloneShallow = cloneShallow;
    }

    public CloneDepth getCloneDepth() {
        return cloneDepth;
    }

    public void setCloneDepth(CloneDepth cloneDepth) {
        this.cloneDepth = cloneDepth;
    }

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

    public Prototype clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
            prototype.setCloneDepth(this.cloneDepth.clone());//深度克隆CloneDepth对象实例
        } catch (CloneNotSupportedException e) {
            throw new RemoteException("克隆Prototype对象实例时出错");
        }
        return prototype;
    }
}
