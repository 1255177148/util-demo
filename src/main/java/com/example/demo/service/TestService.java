package com.example.demo.service;

public interface TestService {
    String test();

    String getForHeader(String name);

    void testPut();

    String testPost();

    void testDelete(String id);

    String testException();
}
