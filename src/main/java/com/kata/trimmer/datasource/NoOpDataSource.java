package com.kata.trimmer.datasource;


import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;

import javax.sql.DataSource;


public class NoOpDataSource extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
    }
}
