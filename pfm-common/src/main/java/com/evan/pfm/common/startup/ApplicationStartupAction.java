package com.evan.pfm.common.startup;

public abstract class ApplicationStartupAction {
	
	public ApplicationStartupAction() {
		ApplicationStartupRegister.getInstance().registerAction(this);
	}
	public void registerStartup() {
		
	}
	
	public abstract void onStartup();
}
