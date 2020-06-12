package com.example.demo.service.strategy.impl;

import com.example.demo.service.strategy.Strategy;

/**
 * @Author hezhan
 * @Date 2019/10/28 14:05
 * 实现算法接口
 */
public class Subtraction implements Strategy {

    /**
     * 实现减法运算
     * @param a
     * @param b
     * @return
     */
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
