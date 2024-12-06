<%@ page import="utils.CookieManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String loginId = CookieManager.readCookie(request,"loginId");

	String cookieCheck ="";
	if(!loginId.equals("")){ // 아이디 값이 있을떄 조건에 걸림
		cookieCheck = "checked";
	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 페이지</h2>
	<form action="IdSaveProcess.jsp" method="post">
		<label>
			아이디 
			<input type="text" name="user_id"  value="<%= loginId %>" /> 
		</label>
		<label>
			<input type="checkbox" name="save_check"  value="Y" <%= cookieCheck %>/>
			 아이디 저장하기
		</label><br/>
		<label>
			비밀번호 
			<input type="password" name="user_pw"/> 
		</label><br/>
		
		<button>로그인</button>
	</form>
	<% %>
</body>
</html>