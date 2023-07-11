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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
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
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
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
        // String base64EncodedJsonStr = Base64.encode(bodyStr, "UTF-8");
        String jsonAes = CrossPlatformAesMd5Utils.aesEncrypt(bodyStr, appKey);
        String jsonStr = Base64Encoder.encode(jsonAes, StandardCharsets.UTF_8);
        String encryptedAppId = CrossPlatformAesMd5Utils.aesEncrypt(appId, appKey);
        // String param = "appId=" + appId + "&ts=" + current + "&base64JsonBody=" +
        // base64EncodedJsonStr + "&appKey=" + encryptedAppKey;
        String MD5Sign = CrossPlatformAesMd5Utils.md5Encrypt(bodyStr);
        String ts = CrossPlatformAesMd5Utils.aesEncrypt(current, appKey);
        String queryParam = "appId=" + encryptedAppId + "&ts=" + ts + "&sign=" + MD5Sign + "&areaCode=373900";
        System.out.println("appId= " + encryptedAppId);
        System.out.println(queryParam);
        System.out.println(jsonStr);
        System.out.println(Pattern
                .matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$", jsonStr));
        String url = "http://ice.natapp4.cc/mchsApp/profession/yimiao/selectByCardId?" + queryParam;
        String result = restTemplateUtil.post(url, jsonStr, String.class);
        System.out.println(result);
    }

    /**
     * 获取字符串占用多少字节，kb和M
     * 
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testLength() throws UnsupportedEncodingException {
        String s = "{\"source\":\"1\",\"pageNo\":1,\"pageSize\":20,\"startDate\":\"2023-06\",\"endDate\":\"2023-06\",\"userIdList\":[\"USER0037268312556316,JOBM0000000000000024\",\"USER0323370518497125,JOBM0000000000000024\",\"USER1749374587212982,JOBM0000000000000024\",\"USER1909677914250728,JOBM0000000000000024\",\"USER2172838368931768,JOBM0000000000000024\",\"USER3610394097042929,JOBM0000000000000024\",\"USER5502411420306850,JOBM0000000000000024\",\"USER5694147959110882,JOBM0000000000000024\",\"USER6153757103733839,JOBM0000000000000024\",\"USER6365146516720125,JOBM0000000000000024\",\"USER6411418491562680,JOBM0000000000000024\",\"USER7684181738111800,JOBM0000000000000024\",\"USER9394541765022497,JOBM0000000000000024\",\"USER9627384284888419,JOBM0000000000000024\",\"USER0481189751648032,JOBM0000000000000029\",\"USER1650763465766296,JOBM0000000000000029\",\"USER4481479655816249,JOBM0000000000000029\",\"USER5529145638495369,JOBM0000000000000029\",\"USER6303801356785266,JOBM0000000000000029\",\"USER6610550235699638,JOBM0000000000000029\",\"USER8270821106998637,JOBM0000000000000029\",\"USER8323556836204882,JOBM0000000000000029\",\"USER9173914656208278,JOBM0000000000000029\",\"USER9236011965523714,JOBM0000000000000029\",\"USER9564851014372237,JOBM0000000000000029\",\"USER9998667783735203,JOBM0000000000000029\",\"USER0550421278319277,JOBM0000000000000031\",\"USER1367845237855195,JOBM0000000000000031\",\"USER1505879555208128,JOBM0000000000000031\",\"USER2685520743370587,JOBM0000000000000031\",\"USER3255036872216867,JOBM0000000000000031\",\"USER4546707561343200,JOBM0000000000000031\",\"USER4618112306707342,JOBM0000000000000031\",\"USER6096378091709141,JOBM0000000000000031\",\"USER6626109411402494,JOBM0000000000000031\",\"USER8798919730178045,JOBM0000000000000031\",\"USER8845809635222771,JOBM0000000000000031\",\"USER9964884246821669,JOBM0000000000000031\",\"USER0898438154764536,JOBM0000000000000030\",\"USER1074208825000329,JOBM0000000000000030\",\"USER2127472453784149,JOBM0000000000000030\",\"USER4661439799641513,JOBM0000000000000030\",\"USER5320073786059852,JOBM0000000000000030\",\"USER5326146554249911,JOBM0000000000000030\",\"USER5953727303026679,JOBM0000000000000030\",\"USER6220384718603802,JOBM0000000000000030\",\"USER6898271597075343,JOBM0000000000000030\",\"USER7046980379862277,JOBM0000000000000030\",\"USER7104983547903749,JOBM0000000000000030\",\"USER7633193700187427,JOBM0000000000000030\",\"USER8034615528714601,JOBM0000000000000030\",\"USER9176262616701859,JOBM0000000000000030\",\"USER9725288003429157,JOBM0000000000000030\",\"USER1123641592622704,JOBM0000000000000028\",\"USER1128722095632469,JOBM0000000000000028\",\"USER1139737742420718,JOBM0000000000000028\",\"USER1333340643349606,JOBM0000000000000028\",\"USER1498178336852291,JOBM0000000000000028\",\"USER1782429306663541,JOBM0000000000000028\",\"USER2540666052228746,JOBM0000000000000028\",\"USER2584671169485564,JOBM0000000000000028\",\"USER4631500283461382,JOBM0000000000000028\",\"USER6553088537595162,JOBM0000000000000028\",\"USER6567069153717078,JOBM0000000000000028\",\"USER7576631382055037,JOBM0000000000000028\",\"USER8318564486554735,JOBM0000000000000028\",\"USER8373809995188337,JOBM0000000000000028\",\"USER9229973435291674,JOBM0000000000000028\",\"USER9843925595007618,JOBM0000000000000028\",\"USER1967080870301600,JOBM0000000000000026\",\"USER2939255649973450,JOBM0000000000000026\",\"USER4063397087873822,JOBM0000000000000026\",\"USER5081512989492812,JOBM0000000000000026\",\"USER5698279806994340,JOBM0000000000000026\",\"USER2840823507500577,JOBM0000000000000027\",\"USER3878663747759120,JOBM0000000000000027\",\"USER4057108883619070,JOBM0000000000000027\",\"USER4701303282863898,JOBM0000000000000027\",\"USER5131504631308314,JOBM0000000000000027\",\"USER8909570681483153,JOBM0000000000000027\",\"USER0656505986354446,JOBM0000000000000023\",\"USER1903269211911089,JOBM0000000000000016\",\"USER2008235531243934,JOBM0000000000000016\",\"USER5447479664757859,JOBM0000000000000016\",\"USER5606360806188782,JOBM0000000000000016\",\"USER7879056336842478,JOBM0000000000000016\",\"USER2396974597825649,JOBM0000000000000018\",\"USER7889758463672715,JOBM0000000000000018\",\"USER2986912178741431,JOBM0000000000000020\",\"USER4694610203490585,JOBM0000000000000020\",\"USER4985669306209873,JOBM0000000000000020\",\"USER7658707979563172,JOBM0000000000000020\",\"USER9139842813613554,JOBM0000000000000020\",\"USER5364895899386786,JOBM0000000000000019\",\"USER6581214254533589,JOBM0000000000000019\",\"USER7295808186450419,JOBM0000000000000019\",\"USER9348481371376278,JOBM0000000000000022\",\"USER0656505986354446,JOBM0000000000000002\",\"USER2618713532368073,JOBM0000000000000002\",\"USER7393046016610204,JOBM0000000000000002\",\"USER7841025153604479,JOBM0000000000000002\",\"USER9630356226526587,JOBM0000000000000002\",\"USER5020096988826172,JOBM0000000000000001\",\"USER6335762287296510,JOBM0000000000000001\",\"USER7393046016610204,JOBM0000000000000001\",\"USER7513394168001266,JOBM0000000000000001\",\"USER7841025153604479,JOBM0000000000000001\",\"USER8538988379350915,JOBM0000000000000001\",\"USER9566400985449995,JOBM0000000000000001\"]}";
        int length = s.getBytes("UTF-8").length;
        System.out.println("数据大小为:" + fileSizeConver(String.valueOf(length)));
    }

    /**
     * 获取数据大小
     * @param size
     * @return 如果小于1KB，则返回B；如果小于1MB，则返回KB；如果小于1GB，则返回MB
     */
    private static String fileSizeConver(String size) {
        BigDecimal decimal = new BigDecimal(size);
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        long length = Long.valueOf(size);
        if (length == 0) {
            return wrongSize;
        }
        if (length < 1024) {
            fileSizeString = df.format(size) + "B";
        } else if (length < 1048576) {
            fileSizeString = decimal.divide(new BigDecimal("1024"), 2, RoundingMode.HALF_UP).toPlainString() + "KB";
        } else if (length < 1073741824) {
            fileSizeString = decimal.divide(new BigDecimal("1048576"), 2, RoundingMode.HALF_UP).toPlainString() + "MB";
        } else {
            fileSizeString = decimal.divide(new BigDecimal("1073741824"), 2, RoundingMode.HALF_UP).toPlainString() + "GB";
        }
        return fileSizeString;
    }
}
