package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.StringToDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

/**
 * @Date 2020/8/7 11:10
 * @Author hezhan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StringDemo {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T json2Object(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T)json : objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }

    /**
     * 以中英文逗号和空格（一个或多个）分隔字符串
     */
    @Test
    public void splitDemo() {
        String str = "11,12，13 244, 34   55， 66";
        String regex = ",|，|\\s+";
        String strArr[] = str.split(regex);
        for (int i = 0; i < strArr.length; i++) {
            System.out.println("i=" + i + "val=" + strArr[i]);
        }
    }

    @Test
    public void name() {
        String str = "{\"manifestType\":\"SEND\",\"manifestId\":333333412591,\"remark\":\"柔柔弱弱退单\",\"opTime\":1622537984364,\"hasChargeBack\":true}";
        StringToDate toDate = JSONObject.parseObject(str, StringToDate.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(format.format(toDate.getOpTime()));
    }
}
