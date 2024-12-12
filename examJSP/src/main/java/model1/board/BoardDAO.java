package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext; // application 영역사용

public class BoardDAO extends JDBConnect{
	
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 기능 구현
	
	// 게시물 갯수 세기
	public int selectCount(Map<String,Object> map) {
		
		int totalCount=0;
		String query = "SELECT COUNT(*) FROM board";
		
		//searchWord 키의 값을 찾아라
		// 조건을 지정한경우 - 검색 구문 추가
		// -> 조건에 맞는 갯수를 조회할 수 있도록
		// searchWord 키의 값이 null이 앙니라면 실행하라(값이 있을때 실행하라)
		if (map.get("searchWord")!=null) {
			query += "WHERE" + map.get("searchField")+" "
					+"LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement(); // 쿼리문생성;
			rs = stmt.executeQuery(query);
			rs.next(); // 커서를 첫 번째 행으로 이동
			totalCount = rs.getInt(1); // 첫번째 칼럼 값을 가져옴
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public List<BoardDTO> selectList(Map<String,Object> map) {
	
		// DB에서 조회한 결과물을 담을 변수
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		// 게시물 목록 조회하는 SQL문 생성
		String query = "SELECT * FROM board "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC "; // 게시물 번호 - 내림차순 정렬
        
        try {
			stmt= con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				// DB에서 불러온 값을 DTO 설정
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate")); // 날짜 형식 가져오기
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 목록을 구하는중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
}
