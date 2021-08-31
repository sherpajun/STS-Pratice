package com.example.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MemberController {
	
	
	@RequestMapping("memberInsert")
	public String memberInsert(HttpServletRequest request) {
		return "memberInsert_result";
	}
	
//	@ResponseBody
//	@RequestMapping("memberInsert")
//	public String memberInsert(HttpServletRequest request) {
//		return "회원가입완료";
//	}

}
