package com.example.demo.service.state2.impl;

import com.example.demo.service.state2.State;
import com.example.demo.util.state_patterns.MakeCoffee;

/**
 * @Author hezhan
 * @Date 2020/6/12 16:24
 * 空杯子的状态
 */
public class EmptyCupState implements State {

    @Override
    public void takeCup(MakeCoffee makeCoffee) {
        System.out.println("已经有杯子了，不用再拿了");
    }

    @Override
    public void addInstantCoffee(MakeCoffee makeCoffee) {
        makeCoffee.setState(new InstantCoffeeCupState());
        System.out.println("正在往杯子里加速溶咖啡......");
    }

    @Override
    public void addWater(MakeCoffee makeCoffee) {
        System.out.println("杯子中还需先加速溶咖啡，再加水");
    }

    @Override
    public void enjoyCoffee(MakeCoffee makeCoffee) {
        System.out.println("现在还是空杯子，喝不了咖啡");
    }
}
