package org.javaboy.dd.annotation;


import org.javaboy.dd.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 注解可以加载到service类或方法上，通过value属性指定类或方法应该使用哪个数据源
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DataSource {
    /**
     * 如果一个方法上加了DataSource 注解，但是没有指定数据源名称，那么默认使用master注解
     * @return
     */
    String value() default DataSourceType.DEFAULT_DS_NAME;
}
