package test1.hello;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloTest {
	public static void main(String[] args) {
		Hello h1=new Hello();
		Hello h2=new Hello();
		System.out.println(h1==h2);//false
		System.out.println(h1.getMsg());
		h2.setMsg("hi");
		System.out.println(h1.getMsg());
		
		BeanFactory	factory = new FileSystemXmlApplicationContext("MyBeans.xml");
		Hello h3=(Hello) factory.getBean("h");
		Hello h4=(Hello) factory.getBean("h");
		System.out.println(h3.getMsg());
		h4.setMsg("hi");
		System.out.println(h3.getMsg());
		System.out.println(h3==h4);//true
	}

}
