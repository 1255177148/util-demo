package com.example.demo.util.fengniao;

import com.example.demo.util.fengniao.entity.UpdateChainStoreRequest;
import com.example.demo.util.fengniao.sign.OpenSignHelper;
import com.example.demo.util.fengniao.util.RandomUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date 2021/1/13 11:53
 * @Author hezhan
 * 蜂鸟即配签名测试
 */
public class Demo {

    public final static String APP_ID = "2915747676954637387";
    public final static String ACCESS_TOKEN = "n-c2c73023-f78b-4e75-84e6-40544fef4474-w";

    public static void main(String[] args) throws IOException {
        UpdateChainStoreRequest request = new UpdateChainStoreRequest();
        UpdateChainStoreRequest.UpdateChainStoreRequestData data = new UpdateChainStoreRequest.UpdateChainStoreRequestData();
        data.setChain_store_code("A001");
        data.setPosition_source(3);
        data.setReceiver_longitude("19.690773");
        data.setReceiver_latitude("19.91243");
        request.setData(data);

        int salt = RandomUtils.getInstance().generateValue(1000, 10000);

        Map<String, Object> sigStr = new LinkedHashMap<>();
        sigStr.put("app_id", APP_ID);
        sigStr.put("access_token", ACCESS_TOKEN);        // 需要使用前面请求生成的token
        sigStr.put("data", request.getData());
        sigStr.put("salt", salt);

        String result = OpenSignHelper.generateBusinessSign(sigStr);
        System.out.println(result);
        System.out.println("随机数是: " + salt);
    }
}
