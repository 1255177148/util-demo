package com.example.demo;

import com.example.demo.util.observer_patterns.Buyer;
import com.example.demo.util.observer_patterns.HandChopper;
import com.example.demo.util.observer_patterns.HuaFans;
import com.example.demo.util.observer_patterns.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/11/19 14:11
 * 观察者模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObserverTest {

    @Test
    public void test(){
        Shop shop = new Shop();
        //定义两个具体观察者，即买家
        Buyer hua = new HuaFans("花粉", shop);
        Buyer hand = new HandChopper("剁手党", shop);

        //买家（具体观察者）到店里注册
        shop.register(hand);
        shop.register(hua);

        //商店到货
        shop.setProduct("电脑");
        shop.setProduct("华为手机【mate30 pro】");
    }
}
