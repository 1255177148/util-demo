package com.example.demo.aspect;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/8/13 14:41
 * @Author hezhan
 */
@Aspect
@Component
@Slf4j
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
        //这一步获取到的方法有可能是代理方法也有可能是真实方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
        if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
            m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
        }
        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        //将参数名称与入参值一一对应起来
        Map<String, Object> params = new HashMap<>();
        if (parameterNames != null && parameterNames.length > 0){
            for (int i = 0; i < parameterNames.length; i++) {
                //这里加一个判断，如果使用requestParam接受参数，加了require=false，这里会存现不存在的现象
                if (args[i] == null){
                    continue;
                }
                //通过所在类转换，获取值，包含各种封装类都可以
                ObjectMapper objectMapper = new ObjectMapper();
                Object o = null;
                try {
                    o = objectMapper.convertValue(args[i],args[i].getClass());
                } catch (Exception e){
                    log.error("获取参数值出错:" + parameterNames[i]);
                    o = "";
                }
                params.put(parameterNames[i], JSON.toJSON(o));
            }
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
        System.out.println("请求的参数为:" + JSON.toJSONString(params));
    }
}
