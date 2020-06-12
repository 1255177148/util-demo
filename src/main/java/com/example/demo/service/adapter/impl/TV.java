package com.example.demo.service.adapter.impl;

import com.example.demo.service.adapter.DualPin;

/**
 * @Author hezhan
 * @Date 2019/10/28 10:57
 * 电视机类，实现了两项插孔接口
 */
public class TV implements DualPin {
    @Override
    public void electrify(int l, int n) {
        System.out.println("火线通电:" + l);
        System.out.println("零线通电:" + n);
    }
}
