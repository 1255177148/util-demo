package com.example.demo.util.fengniao.entity;

import lombok.Data;

/**
 * 抽象request类
 */
@Data
public abstract class AbstractRequest {
    protected String app_id;
    protected int salt;
    protected String signature;
}
