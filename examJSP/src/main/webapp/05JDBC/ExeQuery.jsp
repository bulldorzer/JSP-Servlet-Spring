<%@page import="java.sql.Date"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
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
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%-- DB에 연결 --%>
	<%
	DBConnPool jdbc = new DBConnPool();
	
	// 쿼리문 생성 - 정적쿼리문 생성
	String sql = "SELECT id, pass, name, regidate FROM member";
	jdbc.stmt = jdbc.con.createStatement();
	
	// 쿼리수행 적용시킴 - 몇개 등록되었는지 나옴
	jdbc.rs = jdbc.stmt.executeQuery(sql);
	
	// 결과 확인(웹페이지에 출력)
	while (jdbc.rs.next()){
		String id = jdbc.rs.getString(1);
		String pw = jdbc.rs.getString(2);
		String name = jdbc.rs.getString("name");
		//Date 는 java.sql에 내장되어있는 객체
		Date regidate = jdbc.rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s", id, pw, name, regidate)+"<br/>");
	}
	
	// 연결 닫기
	jdbc.close();
	 %>
</body>
</html>