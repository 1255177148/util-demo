package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:38
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    HttpClientConfig httpClientConfig;

    @Autowired
    OkHttpConfig okHttpConfig;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(httpRequestFactory());
    }

    /**
     * 底层使用OkHttp实现http通信
     * @return
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory(){
        return new OkHttp3ClientHttpRequestFactory(okHttpConfig.okHttpClient());
    }

    /**
     * 底层使用httpClient实现http通信
     * @return
     */
//    @Bean
//    public ClientHttpRequestFactory httpRequestFactory(){
//        return new HttpComponentsClientHttpRequestFactory(httpClientConfig.httpClient());
//    }
}
