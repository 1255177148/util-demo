package com.example.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

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
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            //原有的String是ISO-8859-1编码，去掉
            if (converter instanceof StringHttpMessageConverter){
                iterator.remove();
            }
            //由于系统中默认有jackson 在转换json时自动会启用  但是我们不想使用它 可以直接移除
            if (converter instanceof GsonHttpMessageConverter || converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        messageConverters.add(fastJsonHttpMessageConverter);
        return restTemplate;
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
