package com.evan.pfm.common.dal;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.evan.pfm.common.ApplicationStartupAction;

public class SqlRegister extends ApplicationStartupAction {
	
	
	private Properties properties;
	private Set<String> sqlFilePath = new HashSet<String>();
	
	public synchronized <T> void RegisterSqlFile(Class<T> clazz) {
		sqlFilePath.add(clazz.getInterfaces()[0].getSimpleName());
		this.registerStartup();
	}
	
	@Override
	public void onStartup() {
		AtomicInteger index =  new AtomicInteger(0);
		System.out.println("DAOStartup");
		try {
			String[] path = new String[sqlFilePath.size()];
			for (String sql : sqlFilePath) {
				path[index.getAndIncrement()] = sql;
			}
			this.setProperties(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setProperties(String[] path) throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = new Resource[path.length];
		for (int i = 0; i < path.length; i++) {
			resources[i] = resolver.getResource("classpath:database/sql/" + path[i].replaceAll("DAO", "") + ".properties");
		}
		propertiesFactoryBean.setLocations(resources);
		propertiesFactoryBean.afterPropertiesSet();
		properties = propertiesFactoryBean.getObject();
	}
	public String getSql(String sqlName) {
		return properties.getProperty(sqlName);
	}
}
