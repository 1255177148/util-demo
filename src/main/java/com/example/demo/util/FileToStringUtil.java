package com.example.demo.util;

import com.example.demo.exception.RemoteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author hezhan
 * @Date 2020/5/7 13:50
 * 读取本地文件转为字符串
 */
public class FileToStringUtil {

    public String convertToString(){
        String filePath = "D://access-q.bin";
        File file = new File(filePath);
        System.out.println("文件大小为：" + file.length());
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String content = "";
            StringBuilder sb = new StringBuilder();
            while (content != null){
                content = reader.readLine();
                if (content == null){
                    break;
                }
                sb.append(content.trim());
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            throw new RemoteException("读取文件失败");
        }
    }

    public static void main(String[]args){
        FileToStringUtil util = new FileToStringUtil();
        String str = util.convertToString();
        System.out.println(str);
        try {
            Scanner scanner = new Scanner(new File("D://access-q.bin"));
            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
