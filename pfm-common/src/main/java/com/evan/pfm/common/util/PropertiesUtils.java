package com.evan.pfm.common.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class PropertiesUtils {
	
    private static final PropertiesUtils INSTANCE = new PropertiesUtils();
  
    private PropertiesUtils(){}

    public static PropertiesUtils getInstance(){
        return INSTANCE;
    }
	
	private Properties properties;
	public void setProperties(String[] path) throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		Resource[] resources = new Resource[path.length];
		for(int i=0; i<path.length; i++) {
			resources[i] = resolver.getResource(path[i]);
		}
		propertiesFactoryBean.setLocations(resources);
		propertiesFactoryBean.afterPropertiesSet();
		properties = propertiesFactoryBean.getObject();
	}
	
	public Properties getProperties() {
		return properties;
	}
}
