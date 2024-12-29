package org.javaboy.dd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.javaboy.dd.annotation.DataSource;
import org.javaboy.dd.datasource.DynamicDatasourceContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAspect {
    /**
     * 切点
     * @annotation(org.javaboy.dd.annotation.DataSource) 表示方法上有这个注解，就将这个方法拦截下来
     * @within(org.javaboy.dd.annotation.DataSource)  表示类上有这个注解，就将这个类中的方法拦截下来
     */
    @Pointcut("@annotation(org.javaboy.dd.annotation.DataSource) || @within(org.javaboy.dd.annotation.DataSource)")
    public void pc(){}

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp){
        //获取方法上的有效注解
        DataSource dataSource = getDatasource(pjp);
        if(dataSource!=null){
            //获取注解中的数据源的名称
            String value = dataSource.value();
            DynamicDatasourceContextHolder.setDataSourceType(value);
        }
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            DynamicDatasourceContextHolder.clearDataSourceType();
        }
        return null;

    }

    //先从方法上找，没有再从类上找
    private DataSource getDatasource(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //查找方法里面的注解
        DataSource annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if(annotation !=null){
            //说明方法上有 @DataSource注解
            return annotation;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(),DataSource.class);
    }
}
