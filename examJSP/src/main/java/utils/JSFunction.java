package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+"alert('"+msg+"');"
					+"location.href='"+url+"'"
					+"</script>";
			out.println(script);
		} catch (Exception e) {
			System.out.println("exceptionq발생");
		}
	}
	// 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(String msg, JspWriter out) {
        try {
            String script = "<script>"
                          + "    alert('" + msg + "');"
                          + "    history.back();" // 이전페이로 돌아감
                          + "</script>";
            out.println(script);
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    // HttpServletResponse => 내장객체 response
    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
        	// html의 contentType="text/html; charset=UTF-8" 
            resp.setContentType("text/html;charset=UTF-8"); 
            PrintWriter writer = resp.getWriter();
            String script = "<script>"
                          + "    alert('" + msg + "');"
                          + "    location.href='" + url + "';"
                          + "</script>";
            writer.print(script);
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = "<script>"
                          + "    alert('" + msg + "');"
                          + "    history.back();"
                          + "</script>";
            writer.print(script);
        }
        catch (Exception e) {}
    }
}
