package com.example.demo.service.impl;

import com.example.demo.service.Showable;

/**
 * @Author hezhan
 * @Date 2019/11/4 13:35
 * 口红装饰类
 */
public class Lipstick extends Decorator {

    public Lipstick(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("涂口红(");
        showable.show();
        System.out.print(")");
    }
}
