package com.evan.pfm.common.service;

import org.springframework.stereotype.Component;

import com.evan.pfm.common.startup.ApplicationStartupAction;

@Component
public class ServiceStartup extends ApplicationStartupAction {
	
	
	@Override
	public void onStartup() {
		System.out.println("ServiceStartup");
	}
}
