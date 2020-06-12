package com.example.demo.util.state_patterns;

import com.example.demo.service.state2.State;
import com.example.demo.service.state2.impl.NoCupState;

/**
 * @Author hezhan
 * @Date 2020/6/12 15:58
 * 状态模式的context类，
 * 拥有不同状态的对象，负责调用状态类接口，
 * 来实现切换不同状态实现不同动作的功能
 */
public class MakeCoffee {

    private State state;

    public MakeCoffee() {
        this.state = new NoCupState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * 拿杯子
     */
    public void takeCup(){
        state.takeCup(this);
    }

    /**
     * 加入速溶咖啡
     */
    public void addInstantCoffee(){
        state.addInstantCoffee(this);
    }

    /**
     * 加入热水
     */
    public void addWater(){
        state.addWater(this);
    }

    /**
     * 喝咖啡
     */
    public void enjoyCoffee(){
        state.enjoyCoffee(this);
    }
}
