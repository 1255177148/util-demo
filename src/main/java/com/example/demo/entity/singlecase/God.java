package com.example.demo.entity.singlecase;

/**
 * @Author hezhan
 * @Date 2019/10/25 9:48
 */
public class God {

    private static final God god = new God();//自有永有的God单例，饿汉模式

    /**
     * 构造方法私有化
     */
    private God(){

    }

    /**
     * 提供公开获取God实例的方法
     * @return
     */
    public static God getInstance(){
        return god;
    }
}
