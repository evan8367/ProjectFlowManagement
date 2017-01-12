package com.evan.pfm.common.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class PropertiesConfig {
	public Properties getProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		Resource[] resources = {
				resolver.getResource("classpath:config/database/data_source_DEV.properties"),
				resolver.getResource("classpath:config/redis/redis_DEV.properties"),
		};
		
		propertiesFactoryBean.setLocations(resources);
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}
