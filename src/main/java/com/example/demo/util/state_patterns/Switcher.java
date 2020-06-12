package com.example.demo.util.state_patterns;

import com.example.demo.service.state1.State;

/**
 * @Author hezhan
 * @Date 2019/10/30 13:43
 * 状态模式--环境类，Context
 * Context类里引用状态接口，用来切换不同的状态，实现不同的行为。
 * 状态接口中定义方法，同时可以将Context类的对象作为参数，因为状态可以是一个内部连续的情况，
 * 这样就可以在状态A中执行完对应的行为后，引用Context对象切换到状态B执行下一项。
 */
public class Switcher {
    private State state;//引用状态接口对象，实现可以切换不同的状态

    public Switcher(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void switchOn(){
        state.turnOn(this);
    }

    public void switchOff(){
        state.turnOff(this);
    }
}
