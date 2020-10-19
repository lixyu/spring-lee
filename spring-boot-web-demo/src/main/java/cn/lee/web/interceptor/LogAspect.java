package cn.lee.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : Lee
 * @date : 2020-10-16
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before(value = "within(cn.lee.web.controller.*)")
    public void before(JoinPoint joinPoint){
        Object args[] =joinPoint.getArgs();
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();
        log.info("{},{}:请求入参:{}",method.getDeclaringClass().getName(),method.getName(), $.join(args,";"));
    }

    @AfterReturning(value = "within(cn.lee.web.controller.*)",returning = "rvt")
    public void after(JoinPoint joinPoint,Object rvt){
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        log.info("{},{}:返回数据:{}",method.getDeclaringClass().getName(),method.getName(), $.toJson(rvt));
    }
}
