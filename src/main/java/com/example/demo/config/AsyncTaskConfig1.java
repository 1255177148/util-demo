package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


/**
 * @Author hezhan
 * @Date 2020/3/23 15:30
 * 配置定时任务的线程池---方法一
 * 配置TaskScheduler，加入线程池配置
 */
@Configuration
public class AsyncTaskConfig1 {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("util-demo-task");
        return taskScheduler;
    }
}
