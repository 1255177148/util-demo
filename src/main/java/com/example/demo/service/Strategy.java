package com.example.demo.service;

/**
 * 算法标准
 */
public interface Strategy {

    /**
     * 计算方法（a、b为操作数）
     * @param a
     * @param b
     * @return
     */
    int calculate(int a, int b);
}
