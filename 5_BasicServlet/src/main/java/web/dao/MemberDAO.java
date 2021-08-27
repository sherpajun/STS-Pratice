package web.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import web.util.MyException;
import web.vo.Member;

public class MemberDAO {// DataAccessObject
	SqlSessionFactory factory;
	public MemberDAO() throws MyException {
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			FileInputStream in=new FileInputStream("c:\\util\\mybatisConfig.xml");
			factory=builder.build(in);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("DAO 초기화 오류");
		}
	}

	public void memberInsert2(Member m) throws MyException {
		SqlSession stmt = null;
		try {
			stmt=factory.openSession();
			stmt.insert("a.b.c.member_insert", m);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("회원 가입 오류");
		}finally {
			if(stmt!=null) stmt.close();

		}
	}


	public void memberInsert(Member m) {
		// jdbc 6단계
		Connection con = null;
		PreparedStatement stmt = null;

		try {


			// 2. Connection 얻기
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "cdj";
			String pwd = "1234";
			con = DriverManager.getConnection(url, user, pwd);

			// 3. Statement 얻기
			String sql = "insert into member(id,pw,name) values(?,?,?)";
			stmt = con.prepareStatement(sql);

			// 4. sql 전송
			stmt.setString(1, m.getId());
			stmt.setString(2, m.getPw());
			stmt.setString(3, m.getName());
			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {

			}
		}
	}

	public String login(Member m) {
		// jdbc 6단계
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			// 2. Connection 얻기
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "cdj";
			String pwd = "1234";
			con = DriverManager.getConnection(url, user, pwd);

			// 3. Statement 얻기
			String sql = "select name from member where id=? and pw=?";
			stmt = con.prepareStatement(sql);

			// 4. sql 전송
			stmt.setString(1, m.getId());
			stmt.setString(2, m.getPw());

			ResultSet rs=stmt.executeQuery();

			if(rs.next()) {
				String name=rs.getString(1);
				return name;
			}else {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {

			}
		}
	}
}
