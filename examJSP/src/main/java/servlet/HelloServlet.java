package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// HttpServlet 메서드 doGet, doPost
	// get 요청을 하고 싶으면 doGet을 오버라이딩
	// post요청을 처리하고 싶으면 doPost를 오버라이딩
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("message", "Hello Servlet..!!");
		req.getRequestDispatcher("/12Servlet/HelloServlet.jsp").forward(req, resp); // 포워드 이동
	}

}
