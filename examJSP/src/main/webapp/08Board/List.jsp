<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   // DAO를 생성해 DB에 연결
   BoardDAO dao = new BoardDAO(application);
   
   // 사용자가 입력한 검색 조건을 Map에 저장
   Map<String, Object> param = new HashMap<String, Object>(); 
   String searchField = request.getParameter("searchField"); // 폼에서 전송받은 데이터
   String searchWord = request.getParameter("searchWord"); // 폼에서 전송받은 데이터
   if (searchWord != null) {
       param.put("searchField", searchField);
       param.put("searchWord", searchWord);
   }
   
   int totalCount = dao.selectCount(param);  // 게시물 수 확인
   List<BoardDTO> boardLists = dao.selectList(param);  // 게시물 목록 받기
   dao.close();  // DB 연결 닫기
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