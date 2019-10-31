package com.example.demo;

import com.example.demo.service.State;
import com.example.demo.util.SpringUtils;
import com.example.demo.util.state_patterns.Switcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/10/30 17:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StateTest {

    @Test
    public void test(){
        String beanName = "onState";
        //从请求参数中获得状态name
        State state = SpringUtils.getBean(beanName, State.class);
        Switcher switcher = new Switcher(state);
        switcher.switchOn();
        switcher.switchOff();
    }
}
