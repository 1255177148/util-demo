package com.example.demo.util.proxy_patterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/25 14:45
 * 代理模式--动态代理类
 */
public class KeywordFilter implements InvocationHandler {

    private List<String> blackList = Arrays.asList("电影", "游戏", "音乐", "小说");
    private Object origin;// 被代理的对象，猫，交换机等什么都行

    public KeywordFilter(Object origin){
        this.origin = origin;
        System.out.println("开启关键字过滤模式...");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 要被切入方法面之前的业务逻辑
        String arg = args[0].toString();
        for (String keyword : blackList){
            if (arg.contains(keyword)){
                System.out.println("禁止访问：" + arg);
                return null;
            }
        }
        // 调用真实的被代理对象的方法
        return method.invoke(origin, arg);
    }
}
