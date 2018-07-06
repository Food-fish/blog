package com.jijidom.Administration.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String dataSource() default "";//数据源
}
