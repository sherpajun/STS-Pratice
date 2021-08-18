package com.ksa.fullstack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ksa.fullstack.bbs.dao.BBSDao;


@RunWith(SpringJUnit4ClassRunner.class)
//������ 4 ���ʹ� Ŭ���� ���̽��� �׽�Ʈ�� ������
//Spring 4.0.4, context loaders may choose to support path-based and 
//				class-based resources simultaneously
//�̷��Ե� ��� ���� @ContextConfiguration(classes = {AppConfig.class})

//@WebAppConfiguration WAS���� �׽�Ʈ ���� �� �ʿ��� �ֳ����̼���
@WebAppConfiguration
//@ContextConfiguration(locations={"classpath:/root-context.xml","classpath:/bbsServlet-context.xml"})
@ContextConfiguration(locations={"classpath:/springConfig/*.xml"})
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/bbsServlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private BBSDao bbsDao;
	
	@Before
	public void beforeGetTotalCount() {
		logger.info("@Test�޼ҵ� ������ ����˴ϴ�");
	}
	
	@Test
	public void getTotalCountTest() {
		logger.info("�ѱ���  ������ :"+ bbsDao.getTotalCount());
	}

}
