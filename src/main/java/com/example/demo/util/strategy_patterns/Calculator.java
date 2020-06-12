package com.example.demo.util.strategy_patterns;

import com.example.demo.service.strategy.Strategy;

/**
 * @Author hezhan
 * @Date 2019/10/28 14:06
 * 计算器类
 */
public class Calculator {

    private Strategy strategy;//拥有某种算法的策略

    /**
     * 接入算法策略
     * @param strategy
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 返回具体策略的结果
     * @param a
     * @param b
     * @return
     */
    public int getResult(int a, int b){
        return this.strategy.calculate(a, b);
    }
}
