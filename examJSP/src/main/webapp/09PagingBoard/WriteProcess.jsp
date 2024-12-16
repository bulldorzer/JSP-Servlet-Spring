<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> 

<%
	// 폼값 받기
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 폼값을 DTO에 저장
	BoardDTO dto = new BoardDTO();
	dto.setTitle(title);
	dto.setContent(content);
	// 로그인이 되어있다면 세션영역에 로그인 아이디와 네임이 저장되어 있음 -> /06Session/Loginform,LoginProcess.jsp 참고
	dto.setId(session.getAttribute("UserId").toString());
	
	// DAO객체를 통해 DB에 DTO 저장
	BoardDAO dao = new BoardDAO(application);
	int iResult = dao.insertWrite(dto); // 1행추가
	// int iResult = 0;
	/*  // 더미 데이터 100개 생성 
		for(int i=1; i<=100; i++){
		dto.setTitle(title+ " - " + i);
		dto.setContent(content+ " - " + i);
		iResult = dao.insertWrite(dto);
		} 
	*/
	dao.close();
	
	// 성공 or 실패
	if (iResult == 1){
		response.sendRedirect("List.jsp"); // 해당 페이지로 이동
	}else{
		JSFunction.alertBack("글쓰기에 실패하였습니다", out);
	}
%>