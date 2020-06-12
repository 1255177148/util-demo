package com.example.demo.service.adapter;

public interface TriplePin {
    /**
     * 三项插孔接口（火线、零线和地线）
     * @param l
     * @param n
     * @param e
     */
    void electrify(int l, int n, int e);
}
