package com.evan.pfm.common.startup;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		synchronized (event) {
			if (event.getApplicationContext().getParent() == null) {
				System.out.println("ContextRefreshedEvent");
				for (ApplicationStartupAction action : ApplicationStartupRegister.getInstance().getActions()) {
					System.out.println("Call Action" + action.toString());
					action.onStartup();
				}
			}
		}

	}
}
