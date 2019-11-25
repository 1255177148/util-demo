package com.example.demo.util.proxy_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/25 14:44
 * 代理模式--交换机类
 */
public class Switch implements Intranet {
    @Override
    public void fileAccess(String path) {
        System.out.println("访问内网：" + path);
    }
}
