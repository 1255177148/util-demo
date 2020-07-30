package com.example.demo.entity;

import java.util.Objects;

/**
 * @Date 2020/7/30 9:38
 * @Author hezhan
 * 测试在重写equals和hasCode的方法下对象的对比
 */
public class TestEqualsWithCover {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEqualsWithCover that = (TestEqualsWithCover) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
