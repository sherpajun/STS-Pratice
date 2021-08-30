package web.service;

import web.dao.MemberDAO;
import web.util.MyException;
import web.vo.Member;

public class MemberService {
	
	MemberDAO memberDAO;
	
	public MemberService() throws MyException {
		memberDAO=new MemberDAO();
	}
	
	public String login(Member m) throws MyException {
		return memberDAO.login(m);
	}

	public void memberInsert(Member m) throws MyException {
		memberDAO.memberInsert(m);		
	}

}












