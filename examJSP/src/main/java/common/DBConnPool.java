package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con; // DB연결 담당 객체
	public Statement stmt; // 정적쿼리문 실행할때 사용하는 객체 (고정값)
	public PreparedStatement psmt; // 동적 쿼리문 실행할때 사용하는 객체 (변수처럼 값을 받아서 쿼리문 실행)
	public ResultSet rs; // 결과를 저장할때 사용
	
	// 기본생성자
	public DBConnPool() {
		try {
			// 커녁션 풀 (Data Source)열기
			
			// 자바네이밍서비스(JNDI)에서 이름과 실제 객체를 연결해주는 개념 Context
			Context initCtx = new InitialContext();
			// javaLcomp/env 위치 : 웹어플리케이션 root 디렉토리(시작점)
			Context ctx =  (Context)initCtx.lookup("java:comp/env");
			// dbcp_myoracle 자원을 가져와라
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
			
			//커넥션 풀을 통해 연결 얻기
			con = source.getConnection();
			System.out.println(con);
			System.out.println("DB 커넥션 풀 연결 성공");
			
			
		} catch (Exception e) {
			System.out.println("DB 커넥션 풀 연결 살패");
			e.printStackTrace();
		}
	}
	
	// 연결 해재 (자원반납)
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();

			System.out.println("JDBC 자원 해재");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
