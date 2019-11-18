package com.example.demo;

import com.example.demo.service.impl.Addition;
import com.example.demo.service.impl.Subtraction;
import com.example.demo.util.strategy_patterns.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/10/28 14:09
 * 策略模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {

    /**
     * 策略模式实现步骤
     * 1、定义一个接口，策略方法
     * 2、定义不同的实现类实现此接口，并实现不同的策略方法
     * 3、定义策略类，内部引用接口对象属性，并实现setter方法，然后调用接口的策略方法
     * 4、实例化策略类，并向内部应用接口对象注入不同的实现类，实现加载不同策略方法的功能
     */
    @Test
    public void test(){
        Calculator calculator = new Calculator();//实例化计算器
        calculator.setStrategy(new Addition());//加载加法算法
        int result = calculator.getResult(1, 1);//计算
        System.out.println("得到的加法结果为：" + result);

        calculator.setStrategy(new Subtraction());//再次加载减法算法
        result = calculator.getResult(1, 1);//计算
        System.out.println("得到的减法结果为：" + result);
    }
}
