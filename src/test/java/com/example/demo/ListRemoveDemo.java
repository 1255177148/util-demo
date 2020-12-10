package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2020/12/10 14:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListRemoveDemo {

    @Test
    public void demo() {
        Demo demo1 = new Demo("1","1");
        Demo demo2 = new Demo("2", "2");

        List<Demo> demos = new ArrayList<>();
        demos.add(demo1);
        demos.add(demo2);

        List<Demo> demoList = new ArrayList<>();
        demoList.addAll(demos);

        for (Demo demo : demoList){
            if ("1".equals(demo.getId())){
                demos.remove(demo);
            }
        }

        System.out.println(JSON.toJSONString(demos));
        System.out.println(JSON.toJSONString(demoList));
    }
}

class Demo{
    private String id;
    private String name;

    public Demo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
