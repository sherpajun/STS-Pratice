package com.busan.beans;

import org.springframework.stereotype.Component;

@Component
public class Hello implements Service{
private String msg="간장공장공장장";
	
	public String getMessage() {
		return msg;
		
	}
	public void setMsg(String msg) {
		//유효성 검사 필수
		this.msg =msg;
	}

}


