package com.example.demo.service.decorator.impl;

import com.example.demo.service.decorator.Showable;

/**
 * @Author hezhan
 * @Date 2019/11/4 10:15
 */
public class Girl implements Showable {
    @Override
    public void show() {
        System.out.print("素颜");
    }
}
