package com.example.demo;

import com.example.demo.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/11/5 13:19
 * 多数据源redis使用测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test(){
        redisUtil.set("hezhan", "123", 500);
        redisUtil.setToken("hezhan", "123", 60);
    }
}
