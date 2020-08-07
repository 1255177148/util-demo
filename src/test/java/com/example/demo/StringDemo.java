package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2020/8/7 11:10
 * @Author hezhan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringDemo {

    /**
     * 以中英文逗号和空格（一个或多个）分隔字符串
     */
    @Test
    public void splitDemo() {
        String str = "11,12，13 244, 34   55， 66";
        String regex = ",|，|\\s+";
        String strArr[] = str.split(regex);
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("i=" + i + "val=" + strArr[i]);
        }
    }
}
