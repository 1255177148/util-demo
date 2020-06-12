package com.example.demo.service.state1.impl;

import com.example.demo.service.state1.State;
import com.example.demo.util.SpringUtils;
import com.example.demo.util.state_patterns.Switcher;
import org.springframework.stereotype.Service;

/**
 * @Author hezhan
 * @Date 2019/10/30 13:50
 * 实现抽象状态下开灯状态的实现类
 */
@Service
public class OnState implements State {

    @Override
    public void turnOn(Switcher switcher) {
        System.out.println("已经是开灯状态了");
    }

    @Override
    public void turnOff(Switcher switcher) {
        //逻辑代码
        switcher.setState(SpringUtils.getBean("offState", State.class));
        System.out.println("关灯");
    }
}
