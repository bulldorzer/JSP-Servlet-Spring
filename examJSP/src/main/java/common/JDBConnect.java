package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	public Connection con; // DB연결 담당 객체
	public Statement stmt; // 정적쿼리문 실행할때 사용하는 객체 (고정값)
	public PreparedStatement psmt; // 동적 쿼리문 실행할때 사용하는 객체 (변수처럼 값을 받아서 쿼리문 실행)
	public ResultSet rs; // 결과를 저장할때 사용

	// 오라클과 연결하는 방법들
	
	// 기본생성자 방법1)하드코딩
	public JDBConnect() {
		try {

			// JDBC 드라이버 로드
			// Class.forName(오라클드라이버)
			// new 키워드 없이 클래스명을 통해 직접 객체 생성 후 메모리로드
			Class.forName("oracle.jdbc.OracleDriver");

			// DB 연결
			// url = "오라클프로토콜:@호스트명:포트명:sid"
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);

			System.out.println("DB 연결 성공(기본생성자)");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 두번째생성자  방법2)web.xml에서 자료불러옴 
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB연결
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자1)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 세번째 생성자 방법3) application 객체 자체를 생성자에서 넘겨줌 생성자안에서 web.xml에서 application 객체로 값 추출
	public JDBConnect(ServletContext application) {
		try {
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			// DB 연결
			// url = "오라클프로토콜:@호스트명:포트명:sid"
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DB 연결 해재
	// 어플리케이션과 DB간의 연결종료
	// 사용한 리소스(메모리, 네트워크 연결 등) 반환
	// 시스템과 어플리케이션의 효율성 유지, 시스템 과부화 & 메모리 누수 방지
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
