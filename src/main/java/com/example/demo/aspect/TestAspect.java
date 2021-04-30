package com.example.demo.aspect;

import com.example.demo.annotation.Test;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @Author hezhan
 * @Date 2020/8/27 13:48
 */
@Aspect
@Component
public class TestAspect {

    /**
     * 配置切点
     */
    @Pointcut("@annotation(com.example.demo.annotation.Test)")
    public void point(){
        System.out.println("3");
    }

    @Before(value = "point() && @annotation(test)")
    public void before(Test test){
        System.out.println("执行before方法");
        System.out.println("value = " + test.value());
    }

    @After("point()")
    public void after(){
        System.out.println("执行after方法");
    }

    @AfterReturning("point()")
    public void afterReturning(){
        System.out.println("执行afterReturning方法");
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        System.out.println("目标的方法名为：" + signature.getName());
        System.out.println("目标方法所属类的简单类名:" + signature.getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + signature.getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(signature.getModifiers()));
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
        Object object = joinPoint.proceed();
        System.out.println("执行目标方法后......");
        System.out.println("目标方法的原始返回值为：" + object);
        if (object instanceof String){
            object = "更换下返回的结果";
        }
        return object;
    }
}
