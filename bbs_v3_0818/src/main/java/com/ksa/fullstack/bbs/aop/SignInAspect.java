package com.ksa.fullstack.bbs.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



//SignInAspect�� ����Ʈ�ư� �����̽��� ���� �� ����
@Component
@Aspect
public class SignInAspect {
	
//	  AOP ���� (AspectJ ��� ���)
//	  1. ��� �޼ҵ尡(��Ʈ�ѷ�) ����ɶ� ...��ġ ���� : Pointcut
//	  2. Pointcut���� ������ �޼ҵ尡 �����ϱ� ��/�Ŀ� � �ڵ尡 ����ɰ��ΰ� ��������� �� :Advice
	private static final Logger logger = LoggerFactory.getLogger(SignInAspect.class);
	
	@Pointcut("execution(* com.ksa.fullstack.bbs.controller.BBSController.writeForm(..))")
	public void signIn() {
		logger.info("SingInAspect�� writeForm ����Ʈ�� ����");
	}
	
	@Pointcut("execution(* com.ksa.fullstack.bbs.controller.BBSController.write(..))")
	public void checkMethodTime() {
		logger.info("SingInAspect�� write ����Ʈ�� ����");
	}
	
	
	@Around("signIn()")
//	@Around("execution(* com.ksa.fullstack.bbs.controller.BBSController.writeForm(..))")
	public Object signInCheck(ProceedingJoinPoint pjt) throws Throwable{
		HttpSession session = null; 		
		
		for(Object obj :pjt.getArgs() ) {
			if(obj instanceof HttpSession) {
				session=(HttpSession)obj;
			}
		}
		
		if(session.getAttribute("id")==null) {
			return "login";
		}
		
		Object result =pjt.proceed();// Controller�� �޼ҵ� ����
		
		
		// Controller�� �޼ҵ� ����ǰ� �� ���Ŀ� ����� �ڵ�
		return result;
	}
	
	@Around("checkMethodTime()")
	public Object methodTime(ProceedingJoinPoint pjt) throws Throwable{
		long startTime = System.currentTimeMillis();
		
		Object result =pjt.proceed();// Controller�� �޼ҵ� ����
		
		long methodExecutionTime = System.currentTimeMillis()- startTime;
		logger.info("�޼ҵ� ���� �ð��� : " +  methodExecutionTime);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
