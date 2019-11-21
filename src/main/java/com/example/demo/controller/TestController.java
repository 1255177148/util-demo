package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.TestService;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author hezhan
 * @Date 2019/10/17 11:13
 */
@RestController
@RequestMapping("/openApi/v1")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }

    @GetMapping("/testHeader")
    public String testHeader(@RequestParam("name") String name) {
        return testService.getForHeader(name);
    }

    @PutMapping("/testPut")
    public void testPut(){
        testService.testPut();
    }

    @PostMapping("/testPost")
    public String testPost(){
        return testService.testPost();
    }

    @DeleteMapping("/testDelete/{id}")
    public void testDelete(@PathVariable("id") String id){
        testService.testDelete(id);
    }

    @GetMapping("/test/exception")
    public String testException(){
        return testService.testException();
    }

    @GetMapping("/test/redis")
    public void testRedis(){
        redisUtil.set("hezhan", "123", 500);
        redisUtil.setToken("hezhan", "123", 60);
    }

    @PostMapping("/test/post")
    public String post(@RequestBody String user){
        JSONObject jsonObject = JSONObject.parseObject(user);
        return user;
    }
}
