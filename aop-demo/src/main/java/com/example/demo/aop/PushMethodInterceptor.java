package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : Lee
 * @date : 2020-03-27
 */
@Slf4j
@Aspect
@Component
public class PushMethodInterceptor implements MethodInterceptor {

    /*@Pointcut("@annotation(com.example.demo.annotation.Push)")
    private void cut() {
    }

    @After("cut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        String param=JSON.toJSONString(joinPoint.getArgs());
        System.out.println(param);
    }
    @AfterReturning(returning = "ret", pointcut = "cut()")
    public void doAfterReturning(Object ret) {
        String result=JSON.toJSONString(ret);
        System.out.println("方法的返回值 : " + result);
    }*/

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String param= paramToString(methodInvocation.getArguments());
        System.out.println("入参:"+param);
        String result=JSON.toJSONString(methodInvocation.proceed());
        System.out.println("出参:"+result);
        return null;
    }


    private String paramToString(Object[] args) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).forEach((arg) -> {
            try {
                sb.append(JSON.toJSONString(arg)).append(";");
            } catch (Exception var3) {
                sb.append(arg);
            }

        });
        return sb.toString();
    }

}
