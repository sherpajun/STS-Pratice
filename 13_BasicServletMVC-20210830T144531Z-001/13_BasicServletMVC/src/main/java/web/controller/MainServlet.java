package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

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
		PrintWriter out=response.getWriter();
		
		String sign=request.getParameter("sign");
		if(sign==null) {
			out.append("invalid request");
		}else {
			if(sign.equals("login")) {
				String id=request.getParameter("id");
				String pw=request.getParameter("pw");
				Member m=new Member(id,pw);
				System.out.println(m);
				
				JSONObject json=new JSONObject();
				try {
					String name=memberService.login(m);
					if(name!=null) {//로그인 성공
						HttpSession session=request.getSession();
						System.out.println("부여받은 번호:"+session.getId());
						session.setAttribute("member_name", name);
						json.put("msg", name+"님 환영합니다");
						
					}else {
						json.put("error","다시 로그인해주세요");
					}
				} catch (MyException e) {
					json.put("error", e.getMessage());
				}
				out.append(json.toJSONString());
				
			}else if(sign.equals("memberInsert")) {
				String id=request.getParameter("id");
				String pw=request.getParameter("pw");
				String name=request.getParameter("name");
				Member m=new Member(id,pw,name);
				System.out.println(m);
				
				try {
					memberService.memberInsert(m);
					RequestDispatcher disp=request.getRequestDispatcher("index.html");					
					disp.forward(request, response);
				} catch (MyException e) {
					out.append(e.getMessage());
				}
			}else if(sign.equals("logout")) {
				HttpSession session=request.getSession();
				session.invalidate();//세션 무효화
			}
		}//end else
	}//end process

}//end class







