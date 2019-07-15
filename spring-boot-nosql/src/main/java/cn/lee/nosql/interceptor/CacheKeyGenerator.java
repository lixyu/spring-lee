package cn.lee.nosql.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Lee
 * @date 2019-07-15 10:00
 */
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
