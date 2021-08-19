package com.ksa.fullstack;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("동작이됩니다");
		ServletContext st=sce.getServletContext();
		System.out.println(st.getInitParameter("contextConfigLocation"));
		System.out.println(st.getInitParameter("a"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
