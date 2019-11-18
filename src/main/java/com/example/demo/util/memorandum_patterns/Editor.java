package com.example.demo.util.memorandum_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hezhan
 * @Date 2019/11/18 14:11
 * 文档编辑器类
 */
public class Editor {
    private Doc doc;
    private List<History> historyList;//历史记录列表
    private int historyPosition = -1;//历史记录当前位置

    public Editor(Doc doc){
        System.out.println("<<<打开文档"  + doc.getTitle());
        this.doc = doc;
        historyList = new ArrayList<>();//初始化历史记录
        backup();
        show();
    }

    public void append(String txt){
        System.out.println("<<<插入操作");
        doc.setBody(doc.getBody() + txt);
        backup();
        show();
    }

    public void save(){
        System.out.println("<<<存盘操作");
    }

    public void delete(){
        System.out.println("<<<删除操作");
        doc.setBody("");
        backup();
        show();
    }

    /**
     * 撤销操作
     */
    public void undo(){
        System.out.println(">>>撤销操作");
        if (historyPosition == 0){
            return;
        }
        historyPosition--;//历史记录下标位置回滚1
        History history = historyList.get(historyPosition);
        doc.restoreHistory(history);
        show();
    }

    private void show(){
        System.out.println(doc.getBody());
        System.out.println("文章结束>>>\n");
    }

    /**
     * 备份
     */
    private void backup(){
        historyList.add(doc.createHistory());
        historyPosition++;
    }
}
