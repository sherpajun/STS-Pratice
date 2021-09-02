package com.example.web.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.service.BasketService;
import com.example.web.vo.Basket;
import com.example.web.vo.Member;

@RestController
public class BasketController {


	@Autowired
	BasketService basketService;
	@PostMapping("/basketinsert")
	public String basketinsert(@ModelAttribute Basket b,HttpSession session) {
		JSONObject json = new JSONObject();
		Member m = (Member) session.getAttribute("m");
		if(m!=null) {
			b.setMemberId(m.getId());
			System.out.println(b);
			try {
				basketService.basketinsert(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			json.put("msg", "add complte");
		}else {
			json.put("msg", "로그인이 필요합니다");
		}
		return json.toJSONString();
	}


}
