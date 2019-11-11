package com.example.demo.util.combined_model;

/**
 * @Author hezhan
 * @Date 2019/11/11 13:25
 * 叶子构件--叶子节点没有子节点
 */
public class File extends Node {
    public File(String name) {
        super(name);
    }

    @Override
    public void add(Node child) {
        System.out.print("不能添加子节点");
    }

    @Override
    public void ls(int space) {
        super.ls(space);
    }
}
