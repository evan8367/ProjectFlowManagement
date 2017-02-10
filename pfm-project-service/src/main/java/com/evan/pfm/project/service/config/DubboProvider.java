package com.evan.pfm.project.service.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {

	@SuppressWarnings("unused")
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
//		context.start();
		synchronized (DubboProvider.class) {
			while(true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
