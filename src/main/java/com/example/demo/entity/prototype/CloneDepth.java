package com.example.demo.entity.prototype;

import com.example.demo.exception.RemoteException;

/**
 * @Author hezhan
 * @Date 2019/10/23 14:31
 * 用于测试原型设计模式（作为引用对象，深度克隆部分）
 */
public class CloneDepth implements Cloneable {
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

    public CloneDepth clone(){
        CloneDepth cloneDepth = null;
        try {
            cloneDepth = (CloneDepth)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RemoteException("克隆CloneDepth对象实例时出错");
        }
        return cloneDepth;
    }
}
