package com.example.demo.service;

public interface TestService {
    String test();

    String getForHeader(String name);

    void testPut();

    String testPost();

    void testDelete(String id);

    String testException();

    /**
     * 明确的测试发送请求时RestTemplate配置的FastJson起作用没
     * @return
     */
    String testPostForBody();
}
