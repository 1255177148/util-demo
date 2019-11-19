package com.example.demo.util.observer_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 14:07
 * 观察者模式--具体观察者
 */
public class HuaFans extends Buyer {

    public HuaFans(String name, Shop shop) {
        super(name, shop);
    }

    @Override
    public void inform() {
        String product = shop.getProduct();
        if (product.contains("华为手机")){
            System.out.println(name + "购买：" + product);
        }
    }
}
