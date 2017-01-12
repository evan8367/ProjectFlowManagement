package com.evan.pfm.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evan.pfm.common.ApplicationStartupAction;
import com.evan.pfm.common.ApplicationStartupRegister;

@Component
public class ServiceStartup extends ApplicationStartupAction {
	@Autowired
	ApplicationStartupRegister applicationStartupRegister;
	
	@Override
	public void onStartup() {
		System.out.println("ServiceStartup");
	}
}
