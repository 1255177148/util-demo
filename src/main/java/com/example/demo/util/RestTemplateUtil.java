package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:47
 */
@Component
public class RestTemplateUtil {

    @Autowired
    HttpUtil httpUtil;

    @Autowired
    RestTemplate restTemplate;

    public static String KEY_HEADER = "KEY_HEADER";

    public static String KEY_BODY = "KEY_BODY";

    /**
     * 有header参数的get请求
     * @param url
     * @param param
     * @param headerParam
     * @return
     */
    public ResponseEntity<String> getForHeader(String url, Map<String, Object> param, Map<String, String> headerParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (param != null){
            url = httpUtil.parseUrl(url, param);
        }
        if (headerParam != null){
            for (Map.Entry<String, String> entry : headerParam.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    /**
     * 不在header参数的get请求
     * @param url
     * @param param
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String url, Map<String, Object> param, Class<T> clazz){
        if (param != null){
            url = httpUtil.parseUrl(url, param);
        }
        return restTemplate.getForObject(url, clazz);
    }

    public void put(String url, Map<String, Map<String, String>> param){
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerParam = param.get(KEY_HEADER);
        Map<String, String> bodyParam = param.get(KEY_BODY);
        if (headerParam != null){
            for (Map.Entry<String, String> entry : headerParam.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(bodyParam), headers);
        restTemplate.put(url, httpEntity);
    }
}
