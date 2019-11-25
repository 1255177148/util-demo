package com.example.demo.util.flyweight_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/25 10:03
 * 享元设计模式--抽象享元类（用围棋的黑白棋子来模拟）
 */
public abstract class Flyweight {
    private String color;

    public Flyweight(String color) {
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public abstract void operate(int x, int y);
}
