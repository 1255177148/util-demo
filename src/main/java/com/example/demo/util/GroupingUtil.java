package com.example.demo.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author hezhan
 * @Date 2020/4/26 10:21
 * 使用Java8为集合进行分组
 */
public class GroupingUtil {
    private List<Map<String, Object>> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();

    public static void main(String[]args){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("plan_start_time", "2020/04/26");
        map1.put("name", "测试1");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("plan_start_time", "2020/04/26");
        map2.put("name", "测试2");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("plan_start_time", "2020/04/27");
        map3.put("name", "测试3");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        System.out.println(JSON.toJSONString(list));
        Map<Object, List<Map<String, Object>>> listMap = new HashMap<>();
        listMap = list.stream().collect(Collectors.groupingBy(e -> e.get("plan_start_time")));
        System.out.println(JSON.toJSONString(listMap));
    }
}
