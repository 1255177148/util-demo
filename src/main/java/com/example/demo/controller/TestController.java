package com.example.demo.controller;

import com.example.demo.service.TestService;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.ScheduleTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Autowired
    private ScheduleTaskUtil taskUtil;

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

    /**
     * 测试发送请求时RestTemplate配置的FastJson消息转换器
     * @return
     */
    @PostMapping("/test/post")
    public String post(){
        String result = testService.testPostForBody();
        return result;
    }

    @GetMapping("/test/aop")
    public String testAop(){
        return "测试AOP";
    }

    @GetMapping("/test/async")
    public String testAsync() throws ExecutionException, InterruptedException {
        Future<String> result = taskUtil.task4();
        return result.get();
    }
}
