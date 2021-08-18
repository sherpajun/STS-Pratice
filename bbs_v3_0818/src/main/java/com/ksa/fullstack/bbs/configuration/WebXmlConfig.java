package com.ksa.fullstack.bbs.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebXmlConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerDispatcherServlet(servletContext);
		registerCharacterEncodingFilter(servletContext);
	}
	
	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(RootConfig.class);		
		servletContext.addListener(new ContextLoaderListener(context));
		
		AnnotationConfigWebApplicationContext BBSContext =new AnnotationConfigWebApplicationContext();
		BBSContext.register(BBSServletConfig.class);
		ServletRegistration.Dynamic bbsDispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(BBSContext));
		bbsDispatcher.setLoadOnStartup(1);
		bbsDispatcher.addMapping("*.bbs");		
		
//		AnnotationConfigWebApplicationContext CommentContext =new AnnotationConfigWebApplicationContext();
//		BBSContext.register(CommentServletConfig.class);
//		ServletRegistration.Dynamic commentDispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(CommentContext));
//		commentDispatcher.setLoadOnStartup(1);
//		commentDispatcher.addMapping("*.comment");
	}
	
	public void registerCharacterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncodingFilter=servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true,"/*") ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
