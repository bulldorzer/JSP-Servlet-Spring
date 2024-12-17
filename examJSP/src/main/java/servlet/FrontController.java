package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// 한번의 매핑으로 여러가지 요청 처리하기
@WebServlet("*.one")
public class FrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    	String uri = req.getRequestURI();
        
    	// 현재 경로에서 호스트명 제외한 나머지 부분 알아낸, 다음 마지막 슬래시의 인덱스 구함
        // ex) localhost:8080/12Servlet/[regist.one] =>lastSlash [] 찾아낸부분
        int lastSlash = uri.lastIndexOf("/");
        
        // 슬래시 위치 이후 문자 추출(가져오기)
        // String commandStr = regist.one
        String commandStr = uri.substring(lastSlash);

        if (commandStr.equals("/regist.one"))
            registFunc(req);
        else if (commandStr.equals("/login.one"))
            loginFunc(req);
        else if (commandStr.equals("/freeboard.one"))
            freeboardFunc(req);

        req.setAttribute("uri", uri);
        req.setAttribute("commandStr", commandStr); 
        req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);
    }

    // 페이지별 처리 메서드
    void registFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>회원가입</h4>");
    }

    void loginFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>로그인</h4>");
    }

    void freeboardFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>자유게시판</h4>");
    }
}
