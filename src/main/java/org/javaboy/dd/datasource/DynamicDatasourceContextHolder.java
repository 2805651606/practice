package org.javaboy.dd.datasource;

/**
 * 这个类用来存储当前线程所有使用的数据源名称
 */
public class DynamicDatasourceContextHolder {
    private static ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    public static void setDataSourceType(String dsType){
        CONTEXT_HOLDER.set(dsType);
    }
    public static String getDataSourceType(){
        return CONTEXT_HOLDER.get();
    }
    public static void clearDataSourceType(){
        CONTEXT_HOLDER.remove();
    }
}
