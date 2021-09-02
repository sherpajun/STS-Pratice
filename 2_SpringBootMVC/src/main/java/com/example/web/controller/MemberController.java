package com.example.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.service.MemberService;
import com.example.web.vo.Member;


@RestController
public class MemberController {


	//	@RequestMapping("memberInsert")
	//	public String memberInsert(HttpServletRequest request) {
	//		return "memberInsert_result";
	//	}

	//	@ResponseBody
	//	@RequestMapping("memberInsert")
	//	public String memberInsert(HttpServletRequest request) {
	//		return "회원가입완료";
	//	}

	@Autowired
	MemberService memberService;
	
	@PostMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return null;
	}

	@PostMapping("login")
	public String login(@ModelAttribute Member m,HttpSession session) {
		JSONObject json=new JSONObject();
		try {
			System.out.println(m);
			String name=memberService.login(m);


			if(name!=null) {//login ok
				//HttpSession session=request.getSession();
				m.setName(name);
				session.setAttribute("m",m);
				json.put("name", name);	
			}else {
				json.put("errMsg", "빈칸확인");
			}
		}catch(Exception e) {
			json.put("errMsg","로그인 오류");
		}

		return json.toJSONString();
	}







	@PostMapping("memberInsert")
	public String memberInsert(@ModelAttribute Member m) {

		//		String id=req.getParameter("id");
		//		String pw=req.getParameter("pw");
		//		String name=req.getParameter("name");
		//
		//		Member m = new Member(id,pw,name);
		System.out.println(m);

		try {
			memberService.memberInsert(m);
			return "회원가입완료<button onclick='window.close()'>닫기</button>";
		} catch (Exception e) {
			e.printStackTrace();
			return "회원가입자체가 실패하셧습니다:아이디가 중복됩니다 다른아이디를 사용하세요";
		}

		
	}

}
