package com.example.demo;

import com.example.demo.util.flyweight_patterns.FlyweightFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/11/25 10:53
 * 享元模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlyweightTest {

    @Test
    public void test(){
        String white = "白";
        String black = "黑";
        FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();
        flyweightFactory.getChess(white).operate(2, 3);
        flyweightFactory.getChess(black).operate(1, 5);
        flyweightFactory.getChess(white).operate(3, 5);
        flyweightFactory.getChess(black).operate(2, 6);
    }
}
