package com.evan.pfm.common;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRegister {
	static Set<ApplicationStartupAction> actions = new HashSet<ApplicationStartupAction>();
	
	protected synchronized void registerAction(ApplicationStartupAction startupAction) {
		actions.add(startupAction);
	}
	
	protected Set<ApplicationStartupAction> getActions() {
		return actions;
	}
}
