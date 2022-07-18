package com.example.demo.util.stream;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 使用stream统计对象集合中某个属性的值出现的次数
 */
public class ObjectCountByValue {

    public static void main(String[] args){
        User user1 = new User();
        user1.setName("1");
        User user2 = new User();
        user2.setName("1");

        User user3 = new User();
        user3.setName("2");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Map<String, Long> countMap = users.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()));

        for (Map.Entry<String, Long> entry : countMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
