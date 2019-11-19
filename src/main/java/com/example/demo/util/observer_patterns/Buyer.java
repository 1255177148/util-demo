package com.example.demo.util.observer_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 13:56
 * 观察者模式--抽象卖家类（抽象观察者）
 */
public abstract class Buyer {
    protected String name;
    protected Shop shop;

    public Buyer(String name, Shop shop) {
        this.name = name;
        this.shop = shop;
    }

    /**
     * 被观察者即商品发生更新时通知到观察者即买家类后，买家的行为
     */
    public abstract void inform();
}
