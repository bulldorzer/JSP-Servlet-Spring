<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header class="header">
	<div class="inner">
		<nav>
		<% if(session.getAttribute("UserId")==null){ %>
				
				<a href="../06Session/LoginForm.jsp">로그인</a> 
		<% }else{ %>
			<a href="../06Session/Logout.jsp">로그아웃</a>
		<% } %>	 
		
			<a href="../08Board/List.jsp">게시판(페이징X)</a> 
			<a href="">게시판(페이징O)</a>
		</nav>
	</div>
</header>
