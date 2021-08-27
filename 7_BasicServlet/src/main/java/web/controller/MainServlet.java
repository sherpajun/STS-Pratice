package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.MemberService;
import web.util.MyException;
import web.vo.Member;


public class MainServlet extends HttpServlet {
	MemberService memberService;
       
    
    public MainServlet() {
        super();
        try {
			memberService=new MemberService();
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String sign=request.getParameter("sign");
		if(sign==null) {
			out.append("잘못된 요청입니다.");
		}else {
			if(sign.equals("memberInsert")) {
				String id=request.getParameter("id");
				String pw=request.getParameter("pw");
				String name=request.getParameter("name");
				Member m = new Member(id,pw,name);
				System.out.println(m);
				
				try {
					memberService.memberInsert(m);
					out.append("가입성공");
				} catch (MyException e) {
			
					out.append(e.getMessage());
				}				
			}
		}
	}

}
