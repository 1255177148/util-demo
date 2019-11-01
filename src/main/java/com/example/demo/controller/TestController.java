package com.example.demo.controller;

import com.example.demo.service.TestService;
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
}
