package com.example.demo.util.flyweight_patterns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author hezhan
 * @Date 2019/11/25 10:35
 * 享元模式--享元工厂类
 */
public class FlyweightFactory {
    private static final FlyweightFactory FLYWEIGHT_FACTORY = new FlyweightFactory();
    private final Map<String, Chess> chessMap;// 共享池

    private FlyweightFactory(){
        chessMap = new ConcurrentHashMap<>();
    }

    public static FlyweightFactory getInstance(){
        return FLYWEIGHT_FACTORY;
    }

    /**
     * 根据棋子颜色的不同构造不同的对象，放入共享池
     * @param color
     * @return
     */
    public Chess getChess(String color){
        if (!chessMap.containsKey(color)){
            chessMap.put(color, new Chess(color));
        }
        return chessMap.get(color);
    }
}
