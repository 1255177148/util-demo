package com.example.demo.config;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author hezhan
 * @Date 2019/11/20 9:11
 */
@Configuration
public class OkHttpConfig {

    /**
     * 自定义OkHttp3 client配置
     * @return OkHttp3 client
     */
    @Bean
    public OkHttpClient okHttpClient(){
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(5000);// 设置最大请求数
        dispatcher.setMaxRequestsPerHost(3000);// 设置单个ip最大请求数
        return new OkHttpClient().newBuilder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)// 设置连接超时时间
                .readTimeout(15, TimeUnit.SECONDS)// 设置读取超时时间
                .writeTimeout(15, TimeUnit.SECONDS)// 设置写超时时间
                .dispatcher(dispatcher)// 配置分发数
                .connectionPool(pool())// 配置连接池
                .build();
    }

    /**
     * 连接池配置-配置最大空闲连接数、长连接超时时间
     * @return 连接池
     */
    @Bean
    public ConnectionPool pool(){
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }
}
