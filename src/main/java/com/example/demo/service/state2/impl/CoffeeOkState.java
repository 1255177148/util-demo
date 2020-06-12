package com.example.demo.service.state2.impl;

import com.example.demo.service.state2.State;
import com.example.demo.util.state_patterns.MakeCoffee;

/**
 * @Author hezhan
 * @Date 2020/6/12 16:28
 * 加完水，咖啡冲好的状态
 */
public class CoffeeOkState implements State {
    @Override
    public void takeCup(MakeCoffee makeCoffee) {
        System.out.println("咖啡已经冲好了，不用再拿杯子了");
    }

    @Override
    public void addInstantCoffee(MakeCoffee makeCoffee) {
        System.out.println("咖啡已经冲好了，不用再加速溶咖啡了");
    }

    @Override
    public void addWater(MakeCoffee makeCoffee) {
        System.out.println("咖啡已经冲好了，不用再加水了");
    }

    @Override
    public void enjoyCoffee(MakeCoffee makeCoffee) {
        System.out.println("喝咖啡中......");
    }
}
