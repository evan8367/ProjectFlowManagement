package com.evan.pfm.common.config;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.evan.pfm.common.dal.SqlHelper;

@EnableTransactionManagement
public class DataAccessConfig {
	
	@Autowired
	PropertiesConfig propertiesConfig;
	
	@Bean
	public DataSource pfmDataSource() throws PropertyVetoException, IOException {
		System.out.println("Get Data Source");
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(propertiesConfig.getProperties().getProperty("driverClassName"));
		dataSource.setUrl(propertiesConfig.getProperties().getProperty("jdbc.url"));
		dataSource.setUsername(propertiesConfig.getProperties().getProperty("jdbc.username"));
		dataSource.setPassword(propertiesConfig.getProperties().getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	@Bean
	public SqlHelper sqlHelper(DataSource pfmDataSource) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(pfmDataSource);
		SqlHelper sqlHelper = new SqlHelper(jdbcTemplate);
		
		return sqlHelper;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource pfmDataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(pfmDataSource);
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}
}
