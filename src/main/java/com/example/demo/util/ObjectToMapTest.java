package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;

import java.util.Map;

/**
 * 对象转map测试
 * @Author elvis
 * @Date 2021/6/24 19:02
 */
public class ObjectToMapTest {
    public static void main(String[]args){
        User user = new User();
        user.setName("测试");
        user.setAge(2);
        Map<String, Object> map = JSONObject.parseObject(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(map));
        System.out.println(map.get("name"));
    }
}
