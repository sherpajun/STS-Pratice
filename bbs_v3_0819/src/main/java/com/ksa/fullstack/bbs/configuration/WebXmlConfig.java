package com.ksa.fullstack.bbs.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.ksa.fullstack.comment.configuration.CommentServletConfig;

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
		
		AnnotationConfigWebApplicationContext CommentContext =new AnnotationConfigWebApplicationContext();
		CommentContext.register(CommentServletConfig.class);
		ServletRegistration.Dynamic commentDispatcher = servletContext.addServlet("DispatcherServlet1", new DispatcherServlet(CommentContext));
		commentDispatcher.setLoadOnStartup(2);
		commentDispatcher.addMapping("*.comment");
	}
	
	public void registerCharacterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncodingFilter=servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
