package com.example.demo.service.iterator;

/**
 * 抽象迭代器
 */
public interface Iterator<E> {

    /**
     * 是否有下个元素
     * @return
     */
    boolean hasNext();

    /**
     * 返回下一个元素
     * @return
     */
    E next();
}
