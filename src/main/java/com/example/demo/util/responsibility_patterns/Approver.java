package com.example.demo.util.responsibility_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 10:02
 * 审批人抽象类
 */
public abstract class Approver {
    protected String name;// 抽象出审批人的姓名
    protected Approver nextApprover;// 下一个审批人，更高级领导

    public Approver(String name){
        this.name = name;
    }

    public Approver setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
        return this.nextApprover;
    }

    /**
     * 每一步流程内要处理的逻辑
     * @param amount
     */
    public abstract void approve(int amount);
}
