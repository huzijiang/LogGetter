package com.suixingpay.hw.framework.datasource;

import java.util.Map;
import javax.sql.DataSource;

import com.suixingpay.hw.common.config.datasource.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.suixingpay.hw.common.config.datasource.DynamicDataSourceContextHolder;

/**
 * 动态数据源
 * 
 * suixingpay
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources)
    {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}