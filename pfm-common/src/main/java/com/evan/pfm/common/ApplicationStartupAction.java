package com.evan.pfm.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ApplicationStartupAction {
	
	@Autowired
	ApplicationStartupRegister applicationStartupRegister;
	
	@PostConstruct
	public void registerStartup() {
		this.applicationStartupRegister.registerAction(this);
	}
	
	public abstract void onStartup();
}
