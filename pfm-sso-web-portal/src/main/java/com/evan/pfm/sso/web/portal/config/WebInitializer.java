package com.evan.pfm.sso.web.portal.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.evan.pfm.common.config.AppConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do", "*.htm", "/index.htm" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter};
	}

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		super.registerContextLoaderListener(servletContext);
		servletContext.addListener(RequestContextListener.class);
//		servletContext.addListener(SessionListener.class);
	}
}