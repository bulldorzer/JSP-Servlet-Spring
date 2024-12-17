package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DirectServletPrint extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	 	@Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        resp.setContentType("text/html;charset=UTF-8");
	        PrintWriter w = resp.getWriter(); // 브라우저에 출력해주는 역할 객체 생성
	        
	        // 옛 서블릿방식
	        w.println("<html>");
	        w.println("<head><title> DirectServletPrint </title></head>");
	        w.println("<body>");
	        w.println("<h2>서블릿에서 직접 출력</h2>");
	        w.println("<p>JSP로 직접 포워드 하지 않음 , 서블릿 통해 바로 출력</p>");
	        w.println("</body>");
	        w.println("</html>");
	    }
}
