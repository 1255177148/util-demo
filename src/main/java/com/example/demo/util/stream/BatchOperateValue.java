package com.example.demo.util.stream;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用stream 流批量操作对象集合中的值
 * @Author elvis
 * @Date 2021/4/25 10:37
 */
public class BatchOperateValue {

    public static void main(String[] args){
        User user1 = new User();
        user1.setName("1");
        User user2 = new User();
        user2.setName("2");
        User user3 = new User();
        user3.setName("3");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        // 使用 foreach 循环集合的同时，操作对象中的字段
        users.forEach(e -> e.setAge(1));

        System.out.println(JSON.toJSONString(users));
    }
}
