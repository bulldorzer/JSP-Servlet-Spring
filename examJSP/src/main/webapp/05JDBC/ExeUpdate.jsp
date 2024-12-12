<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="common.JDBConnect"%>
<%@ page import="common.DBConnPool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
<!--  -->
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%-- DB에 연결 --%>
	<%
	DBConnPool jdbc = new DBConnPool();
	
	// 테스트용 입력값 준비
	String id = "test4";
	String pass = "1111";
	String name = "test4회원";
	
	//쿼리문 생성 - 동적쿼리문 생성
	String sql = "INSERT INTO member VALUES (?, ?, ?, sysdate)";
	jdbc.psmt = jdbc.con.prepareStatement(sql);
	
	// sql 구문에 해
	jdbc.psmt.setString(1, id);
	jdbc.psmt.setString(2, pass);
	jdbc.psmt.setString(3, name);
	
	//쿼리수행 적용시킴 - 몇개 등록되었는지 나옴
	int inResult = jdbc.psmt.executeUpdate();
	out.println(inResult + "행이 입력되었습니다");
	
	// 연결 닫기
	jdbc.close();
	 %>
</body>
</html>