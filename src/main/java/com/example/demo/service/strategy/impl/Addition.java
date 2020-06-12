package com.example.demo.service.strategy.impl;

import com.example.demo.service.strategy.Strategy;

/**
 * @Author hezhan
 * @Date 2019/10/28 14:02
 * 实现算法接口
 */
public class Addition implements Strategy {

    /**
     * 实现加法运算
     * @param a
     * @param b
     * @return
     */
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
