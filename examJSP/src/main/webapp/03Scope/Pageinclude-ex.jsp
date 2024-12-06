<%@ page import="common.Person" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>inclue페이지</h2>1111
<%
	System.out.println(pageContext.getAttribute("111111"));
	int pInt2 = (Integer)(pageContext.getAttribute("pageInteger"));
	Person pPerson2 = (Person)(pageContext.getAttribute("pagePerson"));
%>

<p><%= pInt2 %></p>
<p><%= (String) (pageContext.getAttribute("pageStr")) %></p>
<p><%= pPerson2.getName()%></p>
</body>
</html>