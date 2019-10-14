package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:55
 */
@Component
public class HttpUtil {

    public final static String CHARSET_NAME="UTF-8";

    /**
     * 替换配置文件中在get请求中url后面封装的参数字段
     * @param url
     * @param paramsMap
     * @return
     */
    public String parseUrl(String url, Map<String, Object> paramsMap) {
        Map<String, String> replaceFields = getUrlReplaceField(url);
        Map<String, String> replaceInfo = new HashMap<>();
        for (Map.Entry<String, String> entry : replaceFields.entrySet()) {
            Object paramValue = paramsMap.get(entry.getKey());
            String value = "";
            if (paramValue != null) {
                value = String.valueOf(paramsMap.get(entry.getKey()));
            }
            replaceInfo.put(entry.getValue(), value);
        }
        for (Map.Entry<String, String> entry : replaceInfo.entrySet()) {
            try {
                url = StringUtils.replace(url, entry.getKey(), URLEncoder.encode(entry.getValue(), CHARSET_NAME));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    private static Map<String, String> getUrlReplaceField(String url) {
        Map<String, String> replaceFields = new HashMap<>();
        replaceFields = getUrlReplaceField(replaceFields, url);
        return replaceFields;

    }

    private static Map<String, String> getUrlReplaceField(Map<String, String> replaceFields, String url) {
        int startIndex = url.indexOf("{");
        int endIndex = url.indexOf("}") + 1;
        if (startIndex > -1 || endIndex > -1) {
            String field = url.substring(startIndex, endIndex);
            String key = field.substring(1, field.length() - 1);
            replaceFields.put(key, field);
            if (endIndex + 1 <= url.length()) {
                getUrlReplaceField(replaceFields, url.substring(endIndex + 1));
            }
        }
        return replaceFields;
    }
}
