package com.example.demo.controller;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hezhan
 * @Date 2019/10/17 11:13
 */
@RestController
@RequestMapping("/openApi/v1")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test(){
        return testService.test();
    }
}
