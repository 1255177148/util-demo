package com.example.demo.util.combined_model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/11 11:17
 * 容器构件--可以包含子节点
 */
public class Folder extends Node {

    //文件夹可以包含子节点（文件或者文件夹）
    private List<Node> childrenNodes = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    public void add(Node child) {
        childrenNodes.add(child);//添加子节点
    }

    @Override
    public void ls(int space) {
        super.ls(space);
        space++;
        for (Node node : childrenNodes){
            node.ls(space);
        }
    }
}
