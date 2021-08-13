package com.ksa.fullstack.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SignInInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// write.bbs ��û�� ������ ��Ʈ�ѷ��� write.bbs ��û�� ó���ϱ� ���� ������ �ڵ�
		if(!request.getMethod().equalsIgnoreCase("GET")) {
			return true;
		}else{
			HttpSession session=request.getSession();
			if(session.getAttribute("id")==null) {
				response.sendRedirect("login.bbs"); //get ��û��
				return false;
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
}
