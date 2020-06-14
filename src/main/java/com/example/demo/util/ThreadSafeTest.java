package com.example.demo.util;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * 线程安全测试类
 */
public class ThreadSafeTest {

    public static void main(String[] args){
        // 要测试的类
        List<Object> list = new Vector<>();

        // 一个线程的计数器
        CountDownLatch count = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++){
            new Thread(() ->{
                for (int j = 0; j < 10;j++){
                    list.add(new Object());
                }
                // 线程执行完毕就从计数器里减去
                count.countDown();
            }).start();
        }
        try {
            // 当1000个线程运行完后再往下执行
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
