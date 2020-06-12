package com.example.demo.service.state2.impl;

import com.example.demo.service.state2.State;
import com.example.demo.util.state_patterns.MakeCoffee;

/**
 * @Author hezhan
 * @Date 2020/6/12 16:24
 * 没有杯子的状态
 */
public class NoCupState implements State {

    @Override
    public void takeCup(MakeCoffee makeCoffee) {
        makeCoffee.setState(new EmptyCupState());
        System.out.println("正在拿杯子......");
    }

    @Override
    public void addInstantCoffee(MakeCoffee makeCoffee) {
        System.out.println("还没有杯子，怎么加速溶咖啡");
    }

    @Override
    public void addWater(MakeCoffee makeCoffee) {
        System.out.println("还没有杯子，怎么加热水");
    }

    @Override
    public void enjoyCoffee(MakeCoffee makeCoffee) {
        System.out.println("还没有杯子，怎么喝咖啡");
    }
}
