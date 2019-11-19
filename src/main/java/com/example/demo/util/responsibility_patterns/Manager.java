package com.example.demo.util.responsibility_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 10:11
 * 经理类
 */
public class Manager extends Approver {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 5000){
            System.out.println("审批通过。经理【" + name + "】");
        } else {
            System.out.println("无权审批，升级处理。【经理" + name + "】");
            this.nextApprover.approve(amount);
        }
    }
}
