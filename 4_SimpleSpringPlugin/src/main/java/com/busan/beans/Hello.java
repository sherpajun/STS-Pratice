package com.busan.beans;

import org.springframework.stereotype.Component;

@Component
public class Hello implements Service{
private String msg="������������";
	
	public String getMessage() {
		return msg;
		
	}
	public void setMsg(String msg) {
		//��ȿ�� �˻� �ʼ�
		this.msg =msg;
	}

}


