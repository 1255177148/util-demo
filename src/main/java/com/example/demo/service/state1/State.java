package com.example.demo.service.state1;

import com.example.demo.util.state_patterns.Switcher;

/**
 * 抽象出来的状态接口类
 */
public interface State {

    /**
     * 开灯行为--状态引导行为
     * @param switcher
     */
    void turnOn(Switcher switcher);

    /**
     * 关灯行为--状态引导行为
     * @param switcher
     */
    void turnOff(Switcher switcher);
}
