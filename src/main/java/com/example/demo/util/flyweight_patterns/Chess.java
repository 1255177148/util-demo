package com.example.demo.util.flyweight_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/25 10:32
 * 享元模式--具体享元对象
 */
public class Chess extends Flyweight {

    public Chess(String color) {
        super(color);
        System.out.println("从内存中加载" + color + "棋");
    }

    @Override
    public void operate(int x, int y) {
        System.out.println(getColor() + "棋=>横：" + x + "，纵：" + y);
    }
}
