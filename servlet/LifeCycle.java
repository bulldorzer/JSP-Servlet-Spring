package servlet;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/12Servlet/LifeCycle.do")
public class LifeCycle extends HttpServlet {
   private static final long serialVersionUID = 1L;

   // 객체 생성 직후 init(), 메서드 호출하기 전에 호출
    @PostConstruct
    public void myPostConstruct() { // 메서드 명은 알아서 작명
        System.out.println("myPostConstruct() 호출");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init() 호출");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("service() 호출");
        super.service(req, resp);
    }        

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("doGet() 호출");
        req.getRequestDispatcher("/12Servlet/LifeCycle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("doPost() 호출");
        req.getRequestDispatcher("/12Servlet/LifeCycle.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy() 호출");
    }

    // destroy()실행후 컨테이너(톰캣) 이 서블릿 객체를 제거하는 과정해서 호출함
    @PreDestroy
    public void myPreDestroy() {
        System.out.println("myPreDestroy() 호출");
    }
}
