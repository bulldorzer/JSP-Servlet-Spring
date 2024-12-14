package model1.board;

import java.io.Console;
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
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능지원)
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
        List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수
        
     // 쿼리문 템플릿  
        String query = " SELECT * FROM ( "
                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                     + "        SELECT * FROM board ";

        // 검색 조건 추가 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        
        query += "      ORDER BY num DESC "
               + "     ) Tb "
               + " ) "
               + " WHERE rNum BETWEEN ? AND ?"; 
        
        try {
			// 쿼리문 완성
        	psmt = con.prepareStatement(query);
        	psmt.setString(1, map.get("start").toString());
        	psmt.setString(2, map.get("end").toString());
        	
        	rs = psmt.executeQuery();
        	
        	while (rs.next()) {
        		// 한 행(게시물 하나)의 데이터를 DTO에 저장
        		BoardDTO dto = new BoardDTO();
        		dto.setNum(rs.getString("num"));
        		dto.setTitle(rs.getString("title"));
        		dto.setContent(rs.getString("content"));
        		dto.setPostdate(rs.getDate("postdate"));
        		dto.setId(rs.getString("id"));
        		dto.setVisitcount(rs.getString("visitcount"));
        		
        		// 반환할 결과 목록 게시물 추가
        		bbs.add(dto);
        	}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
        
        return bbs;
	}
	
	// 게시글 데이터를 받아 DB에 추가합니다
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		System.out.println("1"+dto.getTitle());
		System.out.println("2"+dto.getContent());
		System.out.println("3"+dto.getId());
		try {
			// INSERT 쿼리문 작성
			String query = "INSERT INTO board ( "
							+" num,title,content,id,visitcount) "
							+" VALUES ( "
							+" seq_board_num.NEXTVAL, ?, ?, ?, 0)";
			System.out.println(query);
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물을 찾아 내용을 반환합니다
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query ="SELECT B.*, M.name "
						+" FROM member M INNER JOIN board B "
						+" ON M.id=B.id"
						+" WHERE num=?";
		
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num); // 인파라미터를 일련번호로 설정
			rs = psmt.executeQuery(); // 쿼리 실행
			
			
			
			//결과처리
			if (rs.next()) {
				dto.setNum(rs.getString("num"));
        		dto.setTitle(rs.getString("title"));
        		dto.setContent(rs.getString("content"));
        		dto.setPostdate(rs.getDate("postdate"));
        		dto.setId(rs.getString("id"));
        		dto.setVisitcount(rs.getString("visitcount"));
        		dto.setName(rs.getString("name"));
			}
			
		} catch (Exception e) {
			System.out.println("게시물 내용 반환 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 지정한 게시물의 조회수를 1 증가 시킵니다
	public void updateVisitCount(String num) {
		String query ="UPDATE board SET visitcount=visitcount+1 WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num); // 인파라미터를 일련번호로 설정
			psmt.executeQuery(); // 쿼리 실행
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//지정한 게시물을 수정합니다
    public int updateEdit(BoardDTO dto) {
    	int result = 0;
    	
    	try {
			String query = "UPDATE board SET title=?, content=? WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle()); // 인파라미터를 일련번호로 설정
			psmt.setString(2, dto.getContent()); // 인파라미터를 일련번호로 설정
			psmt.setString(3, dto.getNum()); // 인파라미터를 일련번호로 설정
			System.out.println("query"+query);
			System.out.println("dto.getTitle()"+dto.getTitle());
			System.out.println("dto.getContent()"+dto.getContent());
			System.out.println("dto.getNum()"+dto.getNum());
			result = psmt.executeUpdate(); // 쿼리 실행
		} catch (Exception e) {
			System.out.println("게시물 조회수 수정 중 예외 발생");
			e.printStackTrace();
		}
    	
    	return result;
    }
    
    // 지정한 게시물을 삭제합니다.
    public int deletePost(BoardDTO dto) {
    	int result = 0;
    	
    		try {
    			String query = "DELETE FROM board WHERE num=?";
    			
    			psmt = con.prepareStatement(query);
    			psmt.setString(1, dto.getNum());
    			
    			//쿼리문 실행
    			result = psmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("게시물 조회수 삭제 중 예외 발생");
				e.printStackTrace();
			}
    	return result;
    }
}
