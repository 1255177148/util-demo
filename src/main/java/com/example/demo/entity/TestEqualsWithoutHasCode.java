package com.example.demo.entity;

/**
 * @Date 2020/7/30 9:54
 * @Author hezhan
 * 测试在重写equals方法但没有重写hasCode方法下的对象对比
 */
public class TestEqualsWithoutHasCode {
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
        TestEqualsWithoutHasCode that = (TestEqualsWithoutHasCode) o;
        return name.equals(that.name);
    }
}
