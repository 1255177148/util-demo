package com.example.demo.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author hezhan
 * @Date 2020/3/23 15:11
 * 定时任务类
 * @EnableScheduling 注解用来开启定时任务，基于注解的静态定时任务
 */
@Component
@EnableScheduling
@Async
public class ScheduleTaskUtil {

    @Scheduled(cron = "0/2 * * * * ? ")
    public void task1(){
        System.out.println(Thread.currentThread().getName() + "------------>task1");
    }

    @Scheduled(cron = "0/2 * * * * ? ")
    public void task2(){
        System.out.println(Thread.currentThread().getName() + "------------>task2");
    }

    @Scheduled(cron = "0/2 * * * * ? ")
    public void task3(){
        System.out.println(Thread.currentThread().getName() + "------------>task2");
    }
}
