package com.example.demo;

import com.example.demo.util.memorandum_patterns.Doc;
import com.example.demo.util.memorandum_patterns.Editor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/11/18 14:26
 * 备忘录设计模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemorandumTest {

    @Test
    public void test(){
        Editor editor = new Editor(new Doc("《鸿蒙本纪》"));
        //码字数
        editor.append("第一章 鸿蒙初现");
        editor.append("\n 正文2000字......");
        editor.append("\n第二章 混沌初开");
        editor.append("\n 正文2000字......");
        //不小心删了
        editor.delete();
        //撤销删除操作，找回历史记录内容
        editor.undo();
    }
}
