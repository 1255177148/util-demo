package com.example.demo.util.template_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/1 11:09
 * 模板方法类--模拟做软件项目管理
 */
public abstract class PM {
    protected abstract void analyze();//需求分析
    protected abstract void design();//设计
    protected abstract void develop();//开发
    protected abstract boolean test();//测试
    protected abstract void release();//发布

    /**
     * 为了统一流程规范，制定了一个统一的流程标准，
     * 流程是：需求分析->设计->开发->测试->发布，如果测试不过，转回继续开发
     * 这里使用了final修饰，凡是继承的类都不可重写
     */
    protected final void kickoff(){
        analyze();
        design();
        do {
            develop();
        } while (!test());
        release();
    }
}
