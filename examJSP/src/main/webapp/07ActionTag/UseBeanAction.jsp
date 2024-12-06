<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>액션 태그로 폼값 한 번에 받기</h3>
	<%-- 
		자바빈즈 생성 : scope 지정X, page 영역에 저장
		<jsp:setProperty property="*">
		- * - 와일드 카드
		- 리퀘스트를 통해 들어온 폼값이 자바빈즈에 한번에 저장이됨
		- 폼 값의 속성(name)이 Person 자바빈즈의 멤버변수 이름과 일치하기 때문에
	 --%>
	    <jsp:useBean id="person" class="common.Person" />  
	    <jsp:setProperty property="*" name="person" />  
	    <ul>
	        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  
	        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
	    </ul>
</body>
</html>