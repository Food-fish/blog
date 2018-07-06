package com.jijidom.Administration.config;

import com.jijidom.util.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JinTao
 * @Description: Mybatis多数据源切换
 * @Date: 15:22 2018/6/4
 */
@Configuration
public class DataSourceConfig {

    private final static String READ_DATASOURCE_KEY = "readDataSource";
    //private final static String READ1_DATASOURCE_KEY = "read1DataSource";
    //private final static String READ2_DATASOURCE_KEY = "read2DataSource";

    @Bean
    public AbstractRoutingDataSource routingDataSource(
            @Qualifier("readDataSource") DataSource readDataSource
            //@Qualifier("read1DataSource") DataSource  read1DataSource,
            //@Qualifier("read2DataSource") DataSource  read2DataSource
    ) {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(READ_DATASOURCE_KEY, readDataSource);
        //targetDataSources.put(READ1_DATASOURCE_KEY, read1DataSource);
        //targetDataSources.put(READ2_DATASOURCE_KEY, read2DataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(readDataSource);
        return dataSource;
    }
}
