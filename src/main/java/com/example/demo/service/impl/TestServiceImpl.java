package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import com.example.demo.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:42
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    RestTemplateUtil restTemplateUtil;

    @Override
    public String test() {
        String url = "http://localhost:8762/openApi/v1/test";
        String result = restTemplateUtil.get(url, null, String.class);
        return result;
    }
}
