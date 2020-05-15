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

    private Approver setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
        return this.nextApprover;
    }

    /**
     * 每一步流程内要处理的逻辑
     * @param amount
     */
    public abstract void approve(int amount);

    public static class Builder{
        private Approver head;
        private Approver tail;

        public Builder add(Approver approver){
            if (head == null){
                this.head = this.tail = approver;
                return this;
            }
            // 设置当前链路下一个节点
            this.tail.setNextApprover(approver);
            // 将链路的tail移动到刚添加的新的节点
            this.tail = approver;
            return this;
        }

        public Approver build(){
            return this.head;
        }
    }
}
