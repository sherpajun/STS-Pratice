package my.test2.shape;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		BeanFactory factory=new FileSystemXmlApplicationContext("MyBeans.xml");
		//		Circle c = (Circle)factory.getBean("c");
		//		Rectangle r = (Rectangle)factory.getBean("r");
		//		Triangle t = (Triangle)factory.getBean("t");
		//  ---------------같은 결과-----------------------------
		//		Shape c = (Shape)factory.getBean("c");
		//		Shape r = (Shape)factory.getBean("r");
		//		Shape t = (Shape)factory.getBean("t");
		//출력하면 결과는 똑같다 위에 주석 처리 된 부분이랑
		Shape s = (Shape)factory.getBean(args[0]);
		Printer out = new Printer();
		out.print(s);



		//		Printer out = new Printer();
		//		out.print(c);
		//		out.print(r);
		//		out.print(t);

	}

}
