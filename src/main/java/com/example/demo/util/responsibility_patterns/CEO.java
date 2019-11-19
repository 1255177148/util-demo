package com.example.demo.util.responsibility_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/19 10:14
 * CEO类
 */
public class CEO extends Approver{

    public CEO(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 10000){
            System.out.println("审批通过。【CEO：" + name + "】");
        } else {
            System.out.println("驳回申请。【CEO" + name + "】");
        }
    }
}
