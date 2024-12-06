package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest; // 요청 객체의 클래스
import jakarta.servlet.http.HttpServletResponse; // 응답 객체의 클래스
public class CookieManager {

	// 쿠키 생성
	public static void makeCookie(HttpServletResponse res, String cName, String cValue, int cTime) {
		
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		res.addCookie(cookie); // 응답객체에 추가
	}
	
	// 쿠키 읽기
	public static String readCookie (HttpServletRequest req , String cName) {
		
		String cookieValue ="";
		Cookie[]cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (cookieName.equals(cName)) {
					cookieValue = cookie.getValue();
				}
			}
		}
		return cookieValue;
	}
	
	// 쿠키 삭제
	public static void deleteCookie(HttpServletResponse res, String cName) {
		makeCookie(res, cName,"",0); // 쿠키 시간을 0으로 만들면 삭제와 같음
	}
}
