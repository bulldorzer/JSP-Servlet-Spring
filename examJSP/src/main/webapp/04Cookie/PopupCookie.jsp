<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String chkVal = request.getParameter("inactiveToday");
	// 쿼리스트링에 들어온 자료 타입은?
	// ex) 홍길동 = String / 30 = String / 2024-12-16 = String 으로 받는다
	if(chkVal != null && chkVal.equals("1")){
		// PopupClose이름의 off 값으로 쿠키 생성
		Cookie cookie = new Cookie("PopupClose","off");
		cookie.setPath(request.getContentType());
		cookie.setMaxAge(60*60*24); // 하루동안 유지 (기준:초단위)
		response.addCookie(cookie); // 응답 객체에 쿠키 추가해서 클라이너트에 보내줌
		out.println("쿠키 : 하루동안 열지 않음");//브라우저 텍스트 출력
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>