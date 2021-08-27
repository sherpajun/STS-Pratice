package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
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
        System.out.println("������ ȣ���");
        try {
			memberService=new MemberService();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() ȣ���");
	}


	public void destroy() {
		System.out.println("destroy() ȣ���");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() ȣ���");
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() ȣ���...");    
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("process() ȣ���...");    
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String sign=request.getParameter("sign");
		if(sign==null) {
			//
		}else {
			if(sign.equals("memberInsert")) {
				String id=request.getParameter("id");
				String pw=request.getParameter("pw");
				String name=request.getParameter("name");
				System.out.println(id+":"+pw+":"+name);
				//db�� ���� business
				Member m=new Member(id,pw,name);
				try {
					memberService.memberInsert(m);
					out.append("���� ����");
				} catch (MyException e) {
					out.append(e.getMessage());
				}
				
			}else if(sign.equals("login")) {
				String id=request.getParameter("id");
				String pw=request.getParameter("pw");
				System.out.println(id+":"+pw);
				//db�� ��ȸ business
				Member m=new Member(id,pw,null);
				
				String name=memberService.login(m);
				if(name!=null) {
					out.append(name+"�� ȯ���մϴ�");
				}else {
					out.append("�ٽ� �α������ּ���");
				}
			}
		}
	}


}