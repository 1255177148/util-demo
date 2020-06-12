package com.example.demo;

import com.example.demo.service.iterator.Iterator;
import com.example.demo.service.iterator.impl.DrivingRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/18 10:54
 * 迭代器模式测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IteratorTest {

    @Test
    public void test() {
        DrivingRecorder recorder = new DrivingRecorder();
        //假设记录12条数据，由于记录仪只能记录十条，所以前两条数据会被后两条覆盖
        for (int i = 0; i < 12; i++) {
            recorder.append("视频_" + (i + 1));
        }
        List<String> accidents = new ArrayList<>();
        Iterator<String> iterator = recorder.iterator();
        while (iterator.hasNext()) {
            String video = iterator.next();
            System.out.println(video);
            if ("视频_10".equals(video) || "视频_8".equals(video)) {
                accidents.add(video);
            }
        }
        System.out.println("事故证据：" + accidents);
    }
}
