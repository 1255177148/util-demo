package com.example.demo.util.proxy_patterns;

import java.util.Arrays;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/25 14:23
 * 代理模式--路由器代理类（静态代理）
 */
public class RouterProxy implements Internet {

    private Internet modem;
    private List<String> blackList = Arrays.asList("电影", "游戏", "音乐", "小说");

    public RouterProxy() {
        this.modem = new Modem();// 实例化被代理类
        System.out.println("拨号上网...连接成功");
    }

    @Override
    public void access(String url) {
        for (String keyword : blackList){
            if (url.contains(keyword)){
                System.out.println("禁止访问：" + url);
                return;
            }
        }
        modem.access(url);// 正常访问互联网
    }

    @Override
    public void close(String url) {
        modem.close(url);
    }
}
