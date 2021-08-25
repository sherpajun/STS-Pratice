package com.busan.fullstack;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.busan.beans.Service;

public class HelloTest {
	public static void main(String[]args) {
		BeanFactory factory = new FileSystemXmlApplicationContext("MyBeans.xml");
		
		Service h =(Service)factory.getBean(args[0]);
		System.out.println(h.getMessage());
	}


}
