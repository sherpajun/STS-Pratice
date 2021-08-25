package my.test1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloMain {

	public static void main(String[] args) {
		Hello h1 = new Hello();
		Hello h2 = new Hello();
		System.out.println(h1==h2);
		BeanFactory factory=new FileSystemXmlApplicationContext("MyBeans.xml");
		Hello h = (Hello)factory.getBean("h");//다운캐스팅
				System.out.println(h.getMsg());
				h.setMsg("홍길동");
				System.out.println(h.getMsg());
		// Hellow 객체는 Object이다.
	}

}
