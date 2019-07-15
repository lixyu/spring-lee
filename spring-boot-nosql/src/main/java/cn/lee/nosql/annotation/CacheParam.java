package cn.lee.nosql.annotation;

import java.lang.annotation.*;

/**
 * @author Lee
 * @date 2019-07-15 09:58
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
