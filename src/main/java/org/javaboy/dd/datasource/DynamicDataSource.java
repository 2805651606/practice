package org.javaboy.dd.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(LoadDataSource loadDataSource){
        //1.设置所有数据源
        Map<String, DataSource> allDataSource = loadDataSource.loadAllDataSource();
        super.setTargetDataSources(new HashMap<>(allDataSource));
        //2.设置默认数据源  并非所有方法上都有DataSource注解
        super.setDefaultTargetDataSource(allDataSource.get(DataSourceType.DEFAULT_DS_NAME));
        //3.
        super.afterPropertiesSet();
    }

    /**
     * 这个方法用来返回数据源名称，当系统需要获取数据源的时候，会自动调取该方法获取数据源的名称
     * @return
     */

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceContextHolder.getDataSourceType();
    }
}
