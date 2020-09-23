package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author hezhan
 * @Date 2020/3/23 15:42
 * 配置定时任务的线程池---方法二
 * 配置Executor，加入线程池配置
 * 然后在需要异步执行的类或者方法上加入@Async注解。
 */
@Configuration
@EnableAsync
public class AsyncTaskConfig2 {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("async-");
        // 设置拒绝策略：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
