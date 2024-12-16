<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num = request.getParameter("num"); // 일련번호 받기
	
	BoardDAO dao = new BoardDAO(application);  	// DAO 생성
	dao.updateVisitCount(num); 					// 조회수 증가
	BoardDTO dto = dao.selectView(num);			// 게시물 가져오기
	dao.close();								// DB 연결해재
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<script>
	function deletePost(){
		var confirmed = confirm("정말로 삭제하겠습니까?");
		if (confirmed) {
			var form = document.writerFrm;		// 이름이 writerFrm인 폼 선택
			form.method ="post";				// 전송 방식
			form.action = "DeleteProcess.jsp"; 	// 전송 결로
			form.submit();						// 폼값 전송
		}
	}
</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>회원제 게시판 - 상세보기(View)</h2>
	<form name="writerFrm">
		<input type="hidden" name="num" value="<%= num %>" /> <!-- 공통링크 -->
		
		<table border="1" width="90%">
			<tr>
				<td>번호</td>
				<td><%= dto.getNum() %></td>
				<td>작성자</td>
				<td><%= dto.getId() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%= dto.getPostdate() %></td>
				<td>조회수</td>
				<td><%= dto.getVisitcount() %></td>
			</tr>
			<tr>
				<td>제목</td>			
				<td colspan="3"><%= dto.getTitle() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" height="100">
					<%= dto.getContent().replace("\r\n","<br />") %>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<%
						// 조건1) 로그인되어있나
						// 조건2) 로그인된 아이디와 글작성 아이디가 같은지 확인후 삭제하기 수정하기 버튼 생성
						if(session.getAttribute("UserId") != null && session.getAttribute("UserId").toString().equals(dto.getId())){
					%>
							<button type="button" onclick="location.href='Edit.jsp?num=<%= dto.getNum() %>';">수정하기</button>
							<button type="button" onclick="deletePost();">삭제하기</button>
					<% 
						}
					%>
					<button type="button" onclick="location.href='List.jsp;'">목록보기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>