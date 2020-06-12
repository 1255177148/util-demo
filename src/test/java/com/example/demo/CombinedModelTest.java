package com.example.demo;

import com.example.demo.util.combined_model.File;
import com.example.demo.util.combined_model.Folder;
import com.example.demo.util.combined_model.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hezhan
 * @Date 2019/11/11 13:31
 * 组合模型设计测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CombinedModelTest {

    @Test
    public void test(){
        //根节点
        Node drive = new Folder("D盘");

        Node doc = new Folder("文档");
        doc.add(new File("简历.doc"));
        doc.add(new File("项目介绍.ppt"));

        drive.add(doc);

        Node music = new Folder("音乐");

        Node jay = new Folder("周杰伦");
        jay.add(new File("双节棍.mp3"));
        jay.add(new File("听妈妈的话.mp3"));
        jay.add(new File("本草纲目.mp3"));

        Node song = new Folder("许嵩");
        song.add(new File("毁人不倦.mp3"));
        song.add(new File("违章动物.mp3"));

        music.add(jay);
        music.add(song);

        drive.add(music);
        drive.ls(0);
    }
}
