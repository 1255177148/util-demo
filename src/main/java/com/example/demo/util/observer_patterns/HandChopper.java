package com.example.demo.util.observer_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 14:09
 * 观察者模式--具体观察者
 */
public class HandChopper extends Buyer {

    public HandChopper(String name, Shop shop) {
        super(name, shop);
    }

    @Override
    public void inform() {
        String product = shop.getProduct();
        System.out.println(name + "购买：" + product);
    }
}
