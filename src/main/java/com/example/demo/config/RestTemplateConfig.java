package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:38
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    HttpClientConfig httpClientConfig;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory(){
        return new HttpComponentsClientHttpRequestFactory(httpClientConfig.httpClient());
    }
}
