package com.example.demo.util.fengniao.entity;

import com.example.demo.util.fengniao.util.JsonUtils;
import com.example.demo.util.fengniao.util.URLUtils;
import lombok.Data;

import java.io.IOException;

/**
 * 蜂鸟即配查询服务范围
 */
@Data
public class UpdateChainStoreRequest extends AbstractRequest {

    private UpdateChainStoreRequestData data;

    public String getData() {
        try {
            return URLUtils.getInstance().urlEncode(JsonUtils.getInstance().objectToJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Data
    public static class UpdateChainStoreRequestData {

        private String chain_store_code;

        private Integer position_source;

        private String receiver_longitude;

        private String receiver_latitude;
    }
}
