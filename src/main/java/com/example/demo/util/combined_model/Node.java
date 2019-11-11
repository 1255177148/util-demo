package com.example.demo.util.combined_model;

/**
 * @Author hezhan
 * @Date 2019/11/11 11:13
 * 组合模型--抽象构件，包含子类行为的声明和实现
 */
public abstract class Node {
    protected String name;

    public Node(String name) {
        this.name = name;
    }

    /**
     * 添加后续子节点
     *
     * @param child
     */
    public abstract void add(Node child);

    public void ls(int space) {
        for (int i = 0; i < space; i++) {
            System.out.print("-");//循环输出空格
        }
        System.out.println(name);
    }
}
