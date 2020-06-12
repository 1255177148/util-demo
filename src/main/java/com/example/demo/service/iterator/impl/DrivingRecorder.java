package com.example.demo.service.iterator.impl;

import com.example.demo.service.iterator.Iterator;

/**
 * @Author hezhan
 * @Date 2019/11/14 9:52
 * 迭代器模式--模拟行车记录仪，假设行车记录仪只能记录十条视频，超过后会覆盖最旧的视频，
 * 用户查看的时候会从最新的视频看起。
 */
public class DrivingRecorder {
    private int index = -1;//记录当前位置
    private String[] records = new String[10];//假设只能记录十条视频

    /**
     * 记录视频
     * @param record
     */
    public void append(String record){
        if (index == 9){
            index = 0;//循环覆盖
        } else {
            index++;
        }
        records[index] = record;
    }

    public Iterator<String> iterator(){
        return new Itr();
    }

    class Itr implements Iterator<String> {
        int cursor = index;// 迭代器游标，不染指原始游标
        int loopCount = 0;

        @Override
        public boolean hasNext() {
            return loopCount < 10;
        }

        @Override
        public String next() {
            int i = cursor;//记录即将返回的游标位置
            if (cursor == 0){
                cursor = 9;
            } else {
                cursor--;
            }
            loopCount++;
            return records[i];
        }
    }
}
