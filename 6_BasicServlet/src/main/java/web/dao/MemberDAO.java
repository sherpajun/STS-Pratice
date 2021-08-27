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
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			FileInputStream in = new FileInputStream("c:\\util\\mybatisConfig.xml");
			factory=builder.build(in);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("MemberDAO 초기화 오류");
		}

	}

	public void memberInsert(Member m) throws MyException  {
		SqlSession stmt = null;
		try {
			stmt = factory.openSession();
			stmt.insert("a.b.c.memberInsert",m);
			stmt.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("회원가입오류");
		}finally {
			if(stmt!=null)stmt.close();
		}
	}
}
