package com.example.demo;

import com.example.demo.entity.TestEqualsWithCover;
import com.example.demo.entity.TestEqualsWithoutCover;
import com.example.demo.entity.TestEqualsWithoutHasCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2020/7/30 9:56
 * @Author hezhan
 * 对象实例equals测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqualsDemo {

    @Test
    public void test() {
        /**
         * 测试下没有重写equals和hasCode方法的两个对象实例的比较,
         * 并测试将两个对象实例放入集合中，查看是否会覆盖
         */
        TestEqualsWithoutCover t1 = new TestEqualsWithoutCover();
        t1.setName("he");
        t1.setCode("zhan");
        TestEqualsWithoutCover t2 = new TestEqualsWithoutCover();
        t2.setName("he");
        t2.setCode("zhan");
        HashSet<TestEqualsWithoutCover> s1 = new HashSet<>();
        s1.add(t1);
        s1.add(t2);
        List<TestEqualsWithoutCover> l1 = new ArrayList<>();
        l1.add(t1);
        l1.add(t2);
        Map<TestEqualsWithoutCover, Object> m1 = new HashMap<>();
        m1.put(t1, "");
        m1.put(t2, "123");
        System.out.println("没有重写equals和hasCode方法的对象实例使用==的比较结果：" + (t1 == t2));
        System.out.println("没有重写equals和hasCode方法的对象实例使用equals的比较结果：" + (t1.equals(t2)));
        System.out.println("set size：" + s1.size());
        System.out.println("list size：" + l1.size());
        System.out.println("map size:" + m1.size());

        /**
         * 测试下只重写了equals的两个对象实例的比较,
         * 并测试将两个对象实例放入集合中，查看是否会覆盖
         */
        TestEqualsWithoutHasCode h1 = new TestEqualsWithoutHasCode();
        h1.setName("he");
        h1.setCode("zhan");
        TestEqualsWithoutHasCode h2 = new TestEqualsWithoutHasCode();
        h2.setName("he");
        h2.setCode("xx");
        HashSet<TestEqualsWithoutHasCode> s2 = new HashSet<>();
        s2.add(h1);
        s2.add(h2);
        List<TestEqualsWithoutHasCode> l2 = new ArrayList<>();
        l2.add(h1);
        l2.add(h2);
        Map<TestEqualsWithoutHasCode, Object> m2 = new HashMap<>();
        m2.put(h1, "xx");
        m2.put(h2, "123");
        System.out.println("只重写了equals方法的两个对象实例使用=的比较结果：" + (h1 == h2));
        System.out.println("只重写了equals方法的两个对象实例使用equals的比较结果：" + (h1.equals(h2)));
        System.out.println("set size：" + s2.size());
        System.out.println("list size：" + l2.size());
        System.out.println("map size:" + m2.size());

        /**
         * 测试重写了equals和hasCode方法的两个对象实例的比较,
         * 并测试将两个对象实例放入集合中，查看是否会覆盖
         */
        TestEqualsWithCover w1 = new TestEqualsWithCover();
        w1.setName("he");
        w1.setCode("zhan");
        TestEqualsWithCover w2 = new TestEqualsWithCover();
        w2.setName("he");
        w2.setCode("xx");
        Set<TestEqualsWithCover> s3 = new HashSet<>();
        s3.add(w1);
        s3.add(w2);
        List<TestEqualsWithCover> l3 = new ArrayList<>();
        l3.add(w1);
        l3.add(w2);
        Map<TestEqualsWithCover, Object> m3 = new HashMap<>();
        m3.put(w1, "12");
        m3.put(w2, "123");
        System.out.println("重写了equals和hasCode方法的两个对象实例使用=的比较结果：" + (w1 == w2));
        System.out.println("重写了equals和hasCode方法的两个对象实例使用equals的比较结果：" + (w1.equals(w2)));
        System.out.println("set size：" + s3.size());
        System.out.println("list size：" + l3.size());
        System.out.println("map size:" + m3.size());
    }
}
