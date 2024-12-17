package servlet;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

public class MemberAuth extends HttpServlet {
   private static final long serialVersionUID = 1L;   
    MemberDAO dao;

    @Override
    public void init() throws ServletException {
        // application  내장객체 닫기
        ServletContext application = this.getServletContext();

        // web.xml에서 db 연결정보 얻기
        String driver = application.getInitParameter("OracleDriver");
        String connectUrl = application.getInitParameter("OracleURL");
        String oId = application.getInitParameter("OracleId");
        String oPass = application.getInitParameter("OraclePwd");

        // DAO  생성 => DB 연결
        dao = new MemberDAO(driver, connectUrl, oId, oPass);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //  서블릿 초기화 매개변수에서 관리자 id 받기
    	//  생성된 서블릿 객체 = this = MemberAuth 객체를 말하는 것임 
    	//	-> HttpServlet 상속을 받고 있기에 서블릿 기능을 수행할수 있음
    	//	web.xml에 <init-param> (초기화 매개변수) 태그 안에 내용을
    	//	객체가 생성시 매개변수로 저장
    	//	현재 서블릿 객체에 설정된 초기화 매개변수 중 "admin"라는 이름값을 가져오는것
        String admin_id = this.getInitParameter("admin_id");
        
        //  인증을 요청한 id/패스워드
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        //  회원 테이블에서 인증 요청한 id/패스워드에 해당하는 회원찾기
        MemberDTO memberDTO = dao.getMemberDTO(id, pass);

        // 찾은 회원의 이름에 따른 처리
        String memberName = memberDTO.getName();
        if (memberName != null) {  //  일치하는 회원 찾음
            req.setAttribute("authMessage", memberName + "  회원님 어서오세요 ^^*");
        }
        else {  //  일치하는 회원 없음
            if (admin_id.equals(id))  // 관리자
                req.setAttribute("authMessage", admin_id + " 는 최고 관리자!");
            else  // 비회원
                req.setAttribute("authMessage", "회원이 아닙니다.");
        }
        req.getRequestDispatcher("/12Servlet/MemberAuth.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        dao.close();
    }
}
