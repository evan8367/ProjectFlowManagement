package com.evan.pfm.common;

import java.util.HashSet;
import java.util.Set;


public class ApplicationStartupRegister {
	private static ApplicationStartupRegister instance=new ApplicationStartupRegister();
    private ApplicationStartupRegister(){
        
    }
    public static ApplicationStartupRegister getInstance(){
        return instance;
    }
    
	static Set<ApplicationStartupAction> actions = new HashSet<ApplicationStartupAction>();
	
	protected synchronized void registerAction(ApplicationStartupAction startupAction) {
		actions.add(startupAction);
	}
	
	protected Set<ApplicationStartupAction> getActions() {
		return actions;
	}
}
