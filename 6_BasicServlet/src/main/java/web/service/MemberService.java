package web.service;

import web.dao.MemberDAO;
import web.util.MyException;
import web.vo.Member;

public class MemberService {
MemberDAO memberDAO;
public MemberService() throws MyException {
	memberDAO=new MemberDAO();
}
public void memberInsert(Member m) throws MyException {
	memberDAO.memberInsert(m);
}
}
