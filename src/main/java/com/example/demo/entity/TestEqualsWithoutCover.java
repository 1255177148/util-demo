package com.example.demo.entity;

/**
 * @Date 2020/7/30 9:35
 * @Author hezhan
 * 测试在不重写equals和hasCode的情况下的对象的比较
 */
public class TestEqualsWithoutCover {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
