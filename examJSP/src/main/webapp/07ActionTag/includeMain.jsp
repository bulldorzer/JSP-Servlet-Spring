<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String outerPath1="./OuterPage-1.jsp";
	String outerPath2="./OuterPage-2.jsp";
	
	pageContext.setAttribute("pAttr", "동명왕"); // 페이지 영역에 저장
	request.setAttribute("rAttr", "온조왕"); // request 영역에 저장
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>지시어로 페이지 포함시키기</h2>
	<%@ include file="./OuterPage-1.jsp" %>
	<%-- <%@ include file="<%= outerPath1 %>" %>--%> <%-- 표현식 사용불가 --%>
	<p>외부 파일에 선언한 변수 : <%= newVar1 %></p>
	
	<h2>액션태그로 포함시키기</h2>
	<jsp:include page="./OuterPage-2.jsp"></jsp:include>
	<jsp:include page="<%= outerPath2 %>"></jsp:include> <%-- 표현식 사용가능 --%>
	<p>외부 파일에 선언한 변수 :<%--  <%= newVar2 %>--%></p>
	
	
	
</body>
</html>