<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page import="utils.BoardPage"%>
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
   System.out.println("totalCount: "+totalCount);
   
   // 전체 페이징수 계산
   int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
   // 페이지 블록 숫자
   int blockSize = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
   int totalPage = (int) Math.ceil((double) totalCount / pageSize);
   
   // 현재 페이지 확인
   int pageNum = 1; // 기본값
   String pageTemp = request.getParameter("pageNum");
   
   if(pageTemp != null && !pageTemp.equals("")){
	   pageNum = Integer.parseInt(pageTemp);
   }
   
   // 목록에 출력할 게시물 번호(범위) 계산
   int start = (pageNum -1) * pageSize + 1; // 시작번호
   int end = pageNum * pageSize;
   
   param.put("start", start);
   param.put("end", end);
   
   List<BoardDTO> boardLists = dao.selectListPage(param);  // 게시물 10개씩 목록 받기
   dao.close();  // DB 연결 닫기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	
	<h2>목록보기(List)</h2>
	<!-- 검색폼 -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색하기" />
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 게시물 목록 테이블 (표) -->
	<table border="1" width="90%">
		<!-- 각 컬럼의 이름 -->
		<tr>
			<td width="10%">번호</td>
			<td width="50%">제목</td>
			<td width="15%">작성자</td>
			<td width="10%">방문수</td>
			<td width="15%">작성일</td>
		</tr>
		<!-- 목록의 내용 -->
		<%
			if(boardLists.isEmpty()){ // 게시물이 없을 때
		%>
		<tr>
			<td colspan="5" align="center">
				등록된 게시물이 없습니다.
			</td>
		</tr>
		<%
			}else{
				// 게시물이 있을때
				int virtualNum = 0; // 화면상에서의 게시물 번호
				int countNum = 0;
				for (BoardDTO dto : boardLists){
					virtualNum = totalCount - (((pageNum-1)*pageSize)+countNum++); // 전체 게시물 수에서 시작해 1씩 감소
		%>
			<tr align="center">
				<td><%= virtualNum %></td> <!-- 게시물 번호 -->
				<td align="left"> <!-- 제목 (+하이퍼링크) -->
				<!-- a태그에 쿼리스트링으로 링크를 걸어서 이동(get방식으로 데이터 요청)-> request영역에 보냄 -->
				<!-- 설정하지 않아도 get방식으로 교신을 시작함 -->
					<a href="View.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a>
				</td>
				<td align="center"><%= dto.getId() %></td> <!-- 작성자 아이디 -->
				<td align="center"><%= dto.getVisitcount() %></td> <!-- 조회수 -->
				<td align="center"><%= dto.getPostdate() %></td> <!-- 작성일 -->
			</tr>
		<%
				}
			}
		%>
		
	</table>
	<!-- 목록 하단의 [글쓰기] 버튼 -->
	<table border="1" width="90%">
		<tr>
			<td>
				<%=
						BoardPage.pagingStr(totalCount, pageSize, blockSize ,pageNum, request.getRequestURI())
				%>
			</td>
		</tr>
		<tr align="right">
			<td>
				<button type="button" onclick="location.href='Write.jsp';">글쓰기
				</button>
			</td>
		</tr>
	</table>

</body>
</html>