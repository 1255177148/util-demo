package com.example.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
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

    public static String ERROR_MESSAGE = "ERROR_MESSAGE";

    public static String RESPONSE_RESULT = "RESPONSE_RESULT";

    @Autowired
    CloseableHttpClient httpClient;

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

    public Map<String, String> doGet(String url, Map<String, String> headerParamMap) throws IOException {
        Map<String, String> result = new HashMap<>();
        HttpGet httpGet = new HttpGet(url);
        if (headerParamMap != null) {
            for (Map.Entry<String, String> entry : headerParamMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取结果实体
        result = processResponse(response);
        response.close();
        return result;
    }

    private Map<String, String> processResponse(CloseableHttpResponse response) throws IOException {
        Map<String, String> result = new HashMap<>();
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                String body = EntityUtils.toString(entity, CHARSET_NAME);
                result.put(ERROR_MESSAGE, null);
                result.put(RESPONSE_RESULT, body);
            }
            EntityUtils.consume(entity);
            //释放链接
        } else {
            result.put(ERROR_MESSAGE, String.valueOf(statusCode));
            result.put(RESPONSE_RESULT, response.getStatusLine().getReasonPhrase());
        }
        return result;
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
