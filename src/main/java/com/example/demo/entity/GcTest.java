package com.example.demo.entity;

import lombok.Data;

/**
 * @Date 2020/8/7 10:37
 * @Author hezhan
 * 用来测试GC回收的类
 */
@Data
public class GcTest {
    private String name;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Test类被回收了");
    }
}
