package com.example.demo;

import com.example.demo.util.proxy_patterns.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

/**
 * @Author hezhan
 * @Date 2019/11/25 14:28
 * 代理模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyTest {

    @Test
    public void test() {
        //静态代理测试
        Internet proxy = new RouterProxy();// 实例化代理类
        proxy.access("https://www.电影.com");
        proxy.access("https://www.baidu.com");
        proxy.access("https://www.音乐.com");
        proxy.access("https://www.小说.com");

        //动态代理测试
        Internet internet = (Internet) Proxy.newProxyInstance(Modem.class.getClassLoader(),
                Modem.class.getInterfaces(),
                new KeywordFilter(new Modem()));// 创建动态代理类
        internet.access("https://www.电影.com");
        internet.access("https://www.学习.com");
        internet.access("https://www.音乐.com");
        internet.access("https://www.小说.com");
        internet.close("https://www.电影.com");

        Intranet intranet = (Intranet) Proxy.newProxyInstance(Switch.class.getClassLoader(),
                Switch.class.getInterfaces(),
                new KeywordFilter(new Switch()));
        intranet.fileAccess("\\\\192.68.1.2\\共享\\电影\\IronHuman.mp4");
        intranet.fileAccess("\\\\192.68.1.2\\共享\\音乐\\IronHuman.mp4");
        intranet.fileAccess("\\\\192.68.1.4\\shared\\Java学习资料.zip");
    }
}
