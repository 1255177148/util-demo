package com.example.demo.service.impl;

import com.example.demo.service.Showable;

/**
 * @Author hezhan
 * @Date 2019/11/4 10:17
 * 装饰器
 */
public class Decorator implements Showable {

    protected Showable showable;

    public Decorator(Showable showable) {
        this.showable = showable;
    }

    /**
     * 装饰方法，但不做任何装饰，直接调用，装饰由子类完成
     */
    @Override
    public void show() {
        showable.show();
    }

    /**
     * 简单的装饰，只有一次
     */
//    @Override
//    public void show() {
//        System.out.print("粉饰(");
//        showable.show();
//        System.out.print(")");
//    }
}
