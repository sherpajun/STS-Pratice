package my.test2.shape;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		BeanFactory factory=new FileSystemXmlApplicationContext("MyBeans.xml");
		//		Circle c = (Circle)factory.getBean("c");
		//		Rectangle r = (Rectangle)factory.getBean("r");
		//		Triangle t = (Triangle)factory.getBean("t");
		//  ---------------���� ���-----------------------------
		//		Shape c = (Shape)factory.getBean("c");
		//		Shape r = (Shape)factory.getBean("r");
		//		Shape t = (Shape)factory.getBean("t");
		//����ϸ� ����� �Ȱ��� ���� �ּ� ó�� �� �κ��̶�
		Shape s = (Shape)factory.getBean(args[0]);
		Printer out = new Printer();
		out.print(s);



		//		Printer out = new Printer();
		//		out.print(c);
		//		out.print(r);
		//		out.print(t);

	}

}
