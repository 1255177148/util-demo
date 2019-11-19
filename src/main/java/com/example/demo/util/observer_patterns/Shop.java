package com.example.demo.util.observer_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/19 13:54
 * 观察者模式--商店类（被观察者）
 */
public class Shop {
    private String product;// 模拟商品
    private List<Buyer> buyers;// 买家的引用

    public Shop() {
        this.product = "无商品";
        this.buyers = new ArrayList<>();
    }

    /**
     * 为了主动通知买家即观察者，买家要来这里注册
     * @param buyer
     */
    public void register(Buyer buyer){
        this.buyers.add(buyer);
    }

    public String getProduct(){
        return product;
    }

    public void setProduct(String product){
        this.product = product;// 商店到货了
        notifyBuyers();// 通知所有注册的买家
    }

    /**
     * 通知所有买家
     */
    public void notifyBuyers(){
        buyers.forEach(Buyer::inform);//与下面这种写法等价
//        buyers.stream().forEach(buyer -> buyer.inform());
    }
}
