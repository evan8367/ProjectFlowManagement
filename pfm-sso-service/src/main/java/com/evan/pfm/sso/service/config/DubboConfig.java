package com.evan.pfm.sso.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.evan.pfm.common.config.PropertiesConfig;
import com.evan.pfm.common.startup.ApplicationStartupAction;
import com.evan.pfm.sso.service.intf.UserService;

@Component
public class DubboConfig extends ApplicationStartupAction {

	@Autowired
	PropertiesConfig propertiesConfig;
	ApplicationConfig application;
	RegistryConfig registry;
	MonitorConfig monitor;
	ProtocolConfig protocol;
//	ProtocolConfig hessianProtocol;
	
	@Autowired
	UserService userService;
	@Override
	public void onStartup() {
		this.initConfig();
		this.registerProvider();
	}

	private void initConfig() {
		application = new ApplicationConfig();
		application.setName("pfm-sso-service");
		// 连接注册中心配置
		registry = new RegistryConfig();
		registry.setAddress(propertiesConfig.getProperties().getProperty("dubbo.zookeeper_address"));
		registry.setProtocol("zookeeper");

		monitor = new MonitorConfig();
		monitor.setProtocol("registry");

		protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		String port = propertiesConfig.getProperties().getProperty("dubbo.sso_port");
		protocol.setPort(Integer.valueOf(port));
		protocol.setThreads(200);
		
//		hessianProtocol = new ProtocolConfig();
//		hessianProtocol.setName("hessian");
//		hessianProtocol.setPort(20887);
//		hessianProtocol.setContextpath("dubbo");
//		hessianProtocol.setServer("servlet"); 
	}

	private void registerProvider() {
		// UserServicce
//		List<ProtocolConfig> protocols = new ArrayList<ProtocolConfig>();
//		protocols.add(protocol);
//		protocols.add(hessianProtocol);
		ServiceConfig<UserService> service = new ServiceConfig<UserService>();
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
//		service.setProtocols(protocols); // 多个协议可以用setProtocols()
		service.setProtocol(protocol);
		service.setMonitor(monitor);
		service.setInterface(UserService.class);
		service.setRef(userService);
		service.export();
		System.out.println("UserService exported");
	}

}
