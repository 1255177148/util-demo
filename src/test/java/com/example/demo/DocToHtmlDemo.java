package com.example.demo;

import com.example.demo.util.ConversionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Date 2020/7/31 14:03
 * @Author hezhan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocToHtmlDemo {

    @Autowired
    private ConversionUtil conversionUtil;

    @Test
    public void test() throws Exception {
        File file1 = new File("D:\\测试1.docx");
        File file2 = new File("D:\\测试1.doc");
        String name1 = "测试1.docx";
        String name2 = "测试.doc";
        InputStream inputStream1 = new FileInputStream(file1);
        InputStream inputStream2 = new FileInputStream(file2);
        String response1 = conversionUtil.convertToHtml(inputStream1, name1);
        System.out.println(response1);
        String response2 = conversionUtil.convertToHtml(inputStream2, name2);
        System.out.println(response2);
    }
}
