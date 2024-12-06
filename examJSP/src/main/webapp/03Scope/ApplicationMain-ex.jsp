<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="common.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>application 영역 공유</h2>
<%
	// map : 키:값 쌍으로 저장하는 형태
	// 선언할때 제네릭 2개가 들어가야함 - <key자료형,value자료형>
	Map<String, Person> maps = new HashMap<String, Person>();
	
	// 추가 -put 메서드 사용
	maps.put("p1",new Person("이몽룡",18));
	maps.put("p2",new Person("성춘향",16));
	
	application.setAttribute("maps", maps);
	
%>

	<p>application에 map 저장</p>
</body>
</html>