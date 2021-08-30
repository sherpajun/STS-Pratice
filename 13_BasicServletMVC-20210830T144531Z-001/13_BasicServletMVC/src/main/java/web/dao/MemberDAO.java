package web.dao;

import java.io.FileInputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import web.util.MyException;
import web.vo.Member;

public class MemberDAO {
	
	SqlSessionFactory factory;
	
	public MemberDAO() throws MyException {
		try {
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			factory=builder.build(new FileInputStream("c:\\temp\\mybatisConfig.xml"));
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("MemberDAO 초기화 실패");
		}
	}
	
	public String login(Member m) throws MyException {
		SqlSession stmt=null;
		try {
			 stmt=factory.openSession();
			return stmt.selectOne("member.mapper.login", m);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("login fail");
		}finally {
			if(stmt!=null) stmt.close();
		}
	}

	public void memberInsert(Member m) throws MyException {
		SqlSession stmt=null;
		try {
			 stmt=factory.openSession();
			stmt.insert("member.mapper.memberInsert", m);
			stmt.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("memberInsert fail");
		}finally {
			if(stmt!=null) stmt.close();
		}
		
	}
	
	

}
