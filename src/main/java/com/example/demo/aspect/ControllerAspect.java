package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;

/**
 * @Date 2020/8/13 14:41
 * @Author hezhan
 */
@Aspect
@Component
public class ControllerAspect {

    /**
     * 配置切点
     */
    @Pointcut("execution(public * com.example.demo.controller.TestController.*(..))")
    public void log() {

    }

    /**
     * 前置方法,在目标方法执行前执行
     *
     * @param joinPoint 封装了代理方法信息的对象
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        System.out.println("请求路径为：" + request.getRequestURL());
        System.out.println("request method：" + request.getMethod());
        Signature signature = joinPoint.getSignature();
        System.out.println("目标的方法名为：" + signature.getName());
        System.out.println("目标方法所属类的简单类名:" + signature.getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + signature.getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(signature.getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
    }
}
