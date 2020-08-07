package com.example.demo;

import com.example.demo.entity.GcTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Date 2020/8/7 10:51
 * @Author hezhan
 * GC垃圾回收的demo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GcDemo {

    /**
     * 测试强引用的GC回收
     * 在方法A中使用强引用，即new一个对象，然后方法B调用方法A后，此时方法A已经被调用过，
     * new出来的那个对象就不再使用了，可以被GC回收；
     * 或者在同一个方法内，new出来一个对象，使用完后将此对象赋值null，也可以被GC回收
     */
    @Test
    public void StrongCitation() {
        // 在另一个方法中使用了强引用，即new一个对象，然后现在调用此方法
        test();
        System.gc();
        GcTest gcTest = new GcTest();
        gcTest.setName("测试");
        gcTest = null;
        System.gc();
    }

    private void test(){
        GcTest test = new GcTest();
    }
}
