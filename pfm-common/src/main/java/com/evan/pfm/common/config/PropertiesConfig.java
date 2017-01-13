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
	public Properties getProperties() {
		String environment = "DEV";
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		Resource[] resources = {
				resolver.getResource("classpath:config/database/data_source_"+environment+".properties"),
				resolver.getResource("classpath:config/redis/redis_"+environment+".properties"),
				resolver.getResource("classpath:config/img/img_"+environment+".properties")
		};
		
		propertiesFactoryBean.setLocations(resources);
		try {
			propertiesFactoryBean.afterPropertiesSet();
			return propertiesFactoryBean.getObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
