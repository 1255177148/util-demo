package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author elvis
 * @Date 2021/9/17 10:57
 */
@Data
public class ShareModel {

    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 合同起始时间
     */
    private String contractBeginTime;

    /**
     * 跨境合同起始时间
     */
    private String crossContractBeginTime;

    /**
     * 合同结束时间
     */
    private String contractEndTime;

    /**
     * 跨境合同结束时间
     */
    private String crossContractEndTime;

    private BigDecimal price;

    /**
     * 合同类型 1、首购 2、增购 6、补合同  7、续费
     */
    private Integer contractType;
}
