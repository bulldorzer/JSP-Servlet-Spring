package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect{
	// DB연결
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv,url,id,pw);
	}
	// DB연결 case2
	public MemberDAO(ServletContext application) {
		super(application);
	}
	
	// 명시한 아이디.패스워드와 일치하는 회원 정보를 반환합니다.
	public MemberDTO getMemberDTO(String uid, String upass) {
		
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pass=?";// 쿼리문 템플릿
		
		try {
			//쿼리실행
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, uid); // 쿼리문의 첫 번째 인파라미터에 값 설정
			psmt.setString(2, upass); // 쿼리문의 두 번째 인파라미터에 값 설정
			rs = psmt.executeQuery(); // 쿼리문 실행
			
//			결과처리
			if (rs.next()) {
				// 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
