<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String popupMode = "on";

	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie c : cookies){
			String cookieName = c.getName();
			String cookieValue = c.getValue();
			
			if(cookieName.equals("PopupClose")){
				popupMode = cookieValue;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 통한 팝업 관리</title>
<link rel="stylesheet" href="../css/cookie.css">

</head>
<body>
<main>

	<h2>팝업 메인 페이지</h2>
	<p>팝업 테스트 페이지 입니다.</p>
	<p>현재 팝업 상태 : 
	<%= popupMode %>
	</p>
	
    <div id="popup">
        <h2>공지사항</h2>
        <div>
            <form name="popFrm">
                <label for="">
                    하루 동안 열지 않음
                    <input type="checkbox" id="inactiveToday" value="1">
                </label>
                <button type="button" id="closeBtn">닫기</button>
            </form>
        </div>
    </div>
    <script>
        const closeBtn = document.getElementById('closeBtn');
        const inactiveToday = document.getElementById('inactiveToday');
      	//팝업창 안보이게
        // const popup = document.getElementById('popup');
        // popup.style.display ='none'; //화면에서 안보이게
        const popup = document.getElementById('popup');
        const init = "<%= popupMode %>";
        if (init == "off") {
        	popup.style.display ='none';
		}
        // 닫기 버튼 누르면
        closeBtn.addEventListener('click',function () {
        	
			// 하루 동안 보지 않으면 - 체크 되어 있엇니?
            if (inactiveToday.checked) { // 체크박스가 체크 되어 있으면
                // 브라우저가 업데이트 되지X, 강제로 요청날리는코드
                fetch('./PopupCookie.jsp?inactiveToday=1') //get 방식으로 요청
                    .then(res=>res.text()) // get통신이 요청될때 받을 데이터를 res에 저장
                    // 데이터가 정상적으로 넘어오면(=비어있지 않으면)
                    .then(resData=>location.reload())// 페이지 새로 고침
                    .catch(err=>console.log('Error :', err));
            }
            
            
        });
    </script>
</main>
</body>
</html>
</body>
</html>