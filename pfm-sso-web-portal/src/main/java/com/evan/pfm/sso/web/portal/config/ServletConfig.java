package com.evan.pfm.sso.web.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages = { "com.evan.pfm.common.exception", "com.evan.pfm.common.controller", "com.evan.pfm.sso.web.portal.*" })
public class ServletConfig extends WebMvcConfigurationSupport {
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024 * 1024 * 1024 * 10);
		return resolver;
	}
	
	
}
