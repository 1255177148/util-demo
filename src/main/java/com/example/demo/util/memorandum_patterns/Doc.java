package com.example.demo.util.memorandum_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/18 14:10
 * 文档类
 */
public class Doc {
    private String title;//文章标题
    private String body;//文章内容

    public Doc(String title){
        this.title = title;
        this.body = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 创建历史记录
     * @return
     */
    public History createHistory(){
        return new History(body);
    }

    /**
     * 恢复历史记录
     * @param history
     */
    public void restoreHistory(History history){
        this.body = history.getBody();
    }
}
