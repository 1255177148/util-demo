package com.example.demo.util.template_patterns;

/**
 * @Author hezhan
 * @Date 2019/11/1 11:20
 * 这里是模拟的测试人员，可以为test方法重写，实现测试功能，而且发现kickoff方法并不能重写
 */
public class AutoTestPM extends PM {
    @Override
    protected void analyze() {

    }

    @Override
    protected void design() {

    }

    @Override
    protected void develop() {

    }

    @Override
    protected boolean test() {
        System.out.println("测试人员进行测试");
        return true;
    }

    @Override
    protected void release() {

    }
}
