<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="common.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>application 영역 읽기</h2>
<%
	Map<String,Person> maps = (Map<String,Person>)application.getAttribute("maps");

	// map 데이터에서 key만 추출하기 -
	Set<String> keys = maps.keySet();
	
	for(String key : keys){
	Person p = maps.get(key);
%>
	<p>이름 : <%= p.getName()%> / 나이 : <%= p.getAge()%></p>
<% 
	}
%>

</body>
</html>