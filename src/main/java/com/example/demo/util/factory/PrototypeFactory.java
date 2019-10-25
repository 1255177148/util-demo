package com.example.demo.util.factory;

import com.example.demo.entity.prototype.Prototype;

/**
 * @Author hezhan
 * @Date 2019/10/25 9:34
 * 原型设计工厂类
 */
public class PrototypeFactory {
    //使用饿汉模式造一个原型
    private static Prototype prototype = new Prototype();

    /**
     * 使用原型设计模式克隆实例
     * @return
     */
    public static Prototype getInstance(){
        return prototype.clone();
    }
}
