package com.example.demo;

import cn.hutool.core.codec.Base64Encoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.StringToDate;
import com.example.demo.util.CrossPlatformAesMd5Utils;
import com.example.demo.util.RestTemplateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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

    @Resource
    private RestTemplateUtil restTemplateUtil;

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
        String s = "sdf as xfdf, 111, hjkhkj as 222, 333";
        String arr[] = s.split("as ");
        System.out.println(JSON.toJSONString(arr));

        System.out.println('"' + "abc" + '"');
    }

    @Test
    public void name() {
        String str = "{\"manifestType\":\"SEND\",\"manifestId\":333333412591,\"remark\":\"柔柔弱弱退单\",\"opTime\":1622537984364,\"hasChargeBack\":true}";
        StringToDate toDate = JSONObject.parseObject(str, StringToDate.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(format.format(toDate.getOpTime()));
    }

    @Test
    public void demo() {
        String appId = "fuyoubaojian";
        String appKey = "k4pBynLibLFJd8qQ";
        String current = String.valueOf(System.currentTimeMillis());
        JSONObject bodyMap = new JSONObject();
        bodyMap.put("appId", appId);
        bodyMap.put("personName", "冯雨");
        bodyMap.put("personId", "370402199911106543");
        bodyMap.put("parity", "DM06-05_1");
        bodyMap.put("ts", current);
        bodyMap.put("areaCode", "373900");
        String bodyStr = JSON.toJSONString(bodyMap);
        bodyStr = CrossPlatformAesMd5Utils.sortAsciiJson(bodyStr);
//        String base64EncodedJsonStr = Base64.encode(bodyStr, "UTF-8");
        String jsonAes = CrossPlatformAesMd5Utils.aesEncrypt(bodyStr, appKey);
        String jsonStr = Base64Encoder.encode(jsonAes, StandardCharsets.UTF_8);
        String encryptedAppId = CrossPlatformAesMd5Utils.aesEncrypt(appId, appKey);
//        String param = "appId=" + appId + "&ts=" + current + "&base64JsonBody=" + base64EncodedJsonStr + "&appKey=" + encryptedAppKey;
        String MD5Sign = CrossPlatformAesMd5Utils.md5Encrypt(bodyStr);
        String ts = CrossPlatformAesMd5Utils.aesEncrypt(current, appKey);
        String queryParam = "appId=" + encryptedAppId + "&ts=" + ts + "&sign=" + MD5Sign + "&areaCode=373900";
        System.out.println("appId= " + encryptedAppId);
        System.out.println(queryParam);
        System.out.println(jsonStr);
        System.out.println(Pattern.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$", jsonStr));
        String url = "http://ice.natapp4.cc/mchsApp/profession/yimiao/selectByCardId?" + queryParam;
        String result = restTemplateUtil.post(url, jsonStr, String.class);
        System.out.println(result);
    }
}
