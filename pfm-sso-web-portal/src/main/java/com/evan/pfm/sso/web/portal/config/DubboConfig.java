package com.evan.pfm.sso.web.portal.config;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.evan.pfm.common.startup.ApplicationStartupAction;
import com.evan.pfm.sso.service.intf.UserService;

@Component
public class DubboConfig extends ApplicationStartupAction {

	ApplicationConfig application;
	RegistryConfig registry;
	MonitorConfig monitor;
	ProtocolConfig protocol;
	
	@Override
	public void onStartup() {

	}

	private void initConfig() throws Exception {
		application = new ApplicationConfig();
		application.setName("pfm-sso-service");
		String environment = "DEV";
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = {resolver.getResource("classpath:config/dubbo/dubbo_"+environment+".properties")};
        properties.setLocations(resources);
        properties.afterPropertiesSet();
        Properties property = properties.getObject();
		
		// 连接注册中心配置
		registry = new RegistryConfig();
		registry.setAddress(property.getProperty("dubbo.zookeeper_address"));
		registry.setProtocol("zookeeper");

		monitor = new MonitorConfig();
		monitor.setProtocol("registry");

		protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		String port = property.getProperty("dubbo.sso_port");
		protocol.setPort(Integer.valueOf(port));
		protocol.setThreads(200);
	}

	@Bean
	public UserService userService() throws Exception {
		if (this.application == null) {
			this.initConfig();
		}
		ReferenceConfig<UserService> reference = new ReferenceConfig<UserService>();
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setMonitor(monitor);
		reference.setInterface(UserService.class);
		reference.setCheck(false);
		reference.setTimeout(2000);
		reference.setRetries(0);
		return reference.get();
	}
}
