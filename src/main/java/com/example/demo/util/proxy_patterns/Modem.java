package com.example.demo.util.proxy_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/25 14:22
 * 代理模式--调制解调器（猫）
 */
public class Modem implements Internet {
    @Override
    public void access(String url) {
        System.out.println("正在访问：" + url);
    }

    @Override
    public void close(String url) {
        System.out.println("关闭地址" + url);
    }
}
