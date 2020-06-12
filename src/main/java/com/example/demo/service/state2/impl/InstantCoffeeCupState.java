package com.example.demo.service.state2.impl;

import com.example.demo.service.state2.State;
import com.example.demo.util.state_patterns.MakeCoffee;

/**
 * @Author hezhan
 * @Date 2020/6/12 16:27
 * 加了速溶咖啡的状态
 */
public class InstantCoffeeCupState implements State {

    @Override
    public void takeCup(MakeCoffee makeCoffee) {
        System.out.println("已经有杯子了，不用再拿了");
    }

    @Override
    public void addInstantCoffee(MakeCoffee makeCoffee) {
        System.out.println("已经加过速溶咖啡了，不用再加了");
    }

    @Override
    public void addWater(MakeCoffee makeCoffee) {
        makeCoffee.setState(new CoffeeOkState());
        System.out.println("正在往杯子里加热水......");
    }

    @Override
    public void enjoyCoffee(MakeCoffee makeCoffee) {
        System.out.println("杯子还没有加热水，喝不了咖啡");
    }
}
