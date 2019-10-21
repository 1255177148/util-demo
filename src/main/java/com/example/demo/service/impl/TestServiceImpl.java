package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import com.example.demo.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:42
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    RestTemplateUtil restTemplateUtil;

    public static String KEY_HEADER = "KEY_HEADER";

    public static String KEY_BODY = "KEY_BODY";

    @Override
    public String test() {
        String url = "http://localhost:8762/openApi/v1/test";
        String result = restTemplateUtil.get(url, null, String.class);
        return result;
    }

    @Override
    public String getForHeader(String name) {
        String url = "http://localhost:8762/openApi/v1/testHeader";
        Map<String, String> headerParam = new HashMap<>();
        headerParam.put("name", name);
        ResponseEntity<String> response = restTemplateUtil.getForHeader(url, null, headerParam);
        return response.getBody();
    }

    @Override
    public void testPut() {
        String url = "http://localhost:8762/openApi/v1/testPut";
        Map<String, String> bodyParam = new HashMap<>();
        Map<String, String> headerParam = new HashMap<>();
        bodyParam.put("name", "hezhan");
        headerParam.put("name", "hezhan");
        Map<String, Map<String, String>> param = new HashMap<>();
        param.put(KEY_BODY, bodyParam);
        param.put(KEY_HEADER, headerParam);
        restTemplateUtil.put(url, param);
    }

    @Override
    public String testPost() {
        String url = "http://localhost:8762/openApi/v1/testPost";
        Map<String, String> bodyParam = new HashMap<>();
        Map<String, String> headerParam = new HashMap<>();
        bodyParam.put("name", "hezhan");
        headerParam.put("name", "hezhan");
        Map<String, Map<String, String>> param = new HashMap<>();
        param.put(KEY_BODY, bodyParam);
        param.put(KEY_HEADER, headerParam);
        return restTemplateUtil.post(url, param, String.class);
    }

    @Override
    public void testDelete(String id) {
        String url = "http://localhost:8762/openApi/v1/testDelete/" + id;
        Map<String, String> headerParam = new HashMap<>();
        headerParam.put("name", "hezhan");
        restTemplateUtil.delete(url, headerParam);
    }
}
