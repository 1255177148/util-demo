package com.example.demo.util.memorandum_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/18 14:19
 * 备忘录
 */
public class History {
    private String body;//用于备忘文章内容

    public History(String body){
        this.body = body;
    }

    public String getBody(){
        return body;
    }
}
