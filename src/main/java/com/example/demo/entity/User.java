package com.example.demo.entity;

import java.util.Date;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/10/14 16:45
 */
public class User {
    private String name;
    private Integer age;
    private List<String> list;
    private Date date;
    private String address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
