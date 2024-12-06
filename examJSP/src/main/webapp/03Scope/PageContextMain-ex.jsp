<%@ page import="common.Person" %>    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
//application객체 = 타입 = ServletContext
//page객체 = 타입 = PageContect
pageContext.setAttribute("pageInteger", 1000);
pageContext.setAttribute("pageString", "페이지 영역의 문자열");
pageContext.setAttribute("pagePerson", new Person("한석봉", 99)); 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>page 영역의 속성값 읽기</h2>
<%
	int pInt = (int) (pageContext.getAttribute("pageInteger"));
	String pStr = pageContext.getAttribute("pageString").toString();
	Person person = (Person)(pageContext.getAttribute("pagePerson"));
%>

<p>pInt : <%= pInt %></p>
<p>pStr : <%= pStr %></p>
<p>Person 객체: <%= person.getName() %>,<%=person.getAge() %></p>

<!-- 외부 페이지 포함시키기 -->
<h2>include 된 파일에서 읽기</h2>
<%@ include file="Pageinclude-ex.jsp" %>
<h2>페이지 이동후  page 영역 읽어오기</h2>
<a href="PageLocation.jsp">PageLocation.jsp 바로가기</a>
</body>
</html>