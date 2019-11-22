package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import com.example.demo.service.TestService;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    HttpUtil httpUtil;

    public static String KEY_HEADER = "KEY_HEADER";

    public static String KEY_BODY = "KEY_BODY";

    @Override
    public String test() {
        String url = "http://localhost:8762/openApi/v1/test";
        String result = restTemplateUtil.get(url, String.class, null);
        return result;
    }

    @Override
    public String getForHeader(String name) {
        String url = "http://localhost:8762/openApi/v1/testHeader";
        Map<String, String> headerParam = new HashMap<>();
        headerParam.put("name", name);
        User response = restTemplateUtil.getForHeader(url, headerParam, null, User.class);
        return JSON.toJSONString(response);
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
        bodyParam.put("name", "贺瞻");
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

    @Override
    public String testException() {
        String url = "http://localhost:8762/openApi/v1/test/exception";
        return restTemplateUtil.get(url, String.class, null);
//        Map<String, String> result = new HashMap<>();
//        try {
//            result = httpUtil.doGet(url, null);
//        } catch (Exception e) {
//            throw new RemoteException("访问" + url + "时出现错误，错误原因为：", e);
//        }
//        return "";
    }

    @Override
    public String testPostForBody() {
        String url = "http://localhost:8762/openApi/v1/test/post";
        Map<String, Object> param = new HashMap<>();
        User user = new User();
        user.setName("贺瞻");
        user.setAge(3);
        user.setDate(new Date());
        param.put(KEY_BODY, user);
        return restTemplateUtil.postForBody(url, param, String.class);
    }
}
