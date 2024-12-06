<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>페이지 이동 후 세션 확인</h2>
	<%
		ArrayList<String> lists = (ArrayList<String>) session.getAttribute("lists");
	
		for(String list : lists){
			out.print(list+"<br/>");
		}
		
	%>
	<p>세션 유효기간 : 생성~웹브라우저 완전히 닫을 떄까지 탭만 닫을 경우 - 계속 유지됨</p>
</body>
</html>