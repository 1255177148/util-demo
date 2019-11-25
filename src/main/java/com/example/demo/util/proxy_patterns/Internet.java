package com.example.demo.util.proxy_patterns;

/**
 * 代理模式--互联网访问接口
 */
public interface Internet {
    void access(String url);

    void close(String url);
}
