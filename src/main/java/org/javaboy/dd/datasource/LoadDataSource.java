package org.javaboy.dd.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@EnableConfigurationProperties(DruidProperties.class)
public class LoadDataSource {

    @Autowired
    private DruidProperties druidProperties;

    public Map<String, DataSource> loadAllDataSource() {
        Map<String, DataSource> map = new HashMap<>();
        Map<String, Map<String, String>> ds = druidProperties.getDs();


        try {
            Set<String> keys = ds.keySet();
            for (String key : keys) {
                map.put(key, druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(ds.get(key))));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }


}
